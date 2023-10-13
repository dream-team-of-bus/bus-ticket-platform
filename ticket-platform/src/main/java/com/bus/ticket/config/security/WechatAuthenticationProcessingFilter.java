package com.bus.ticket.config.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bus.ticket.config.wx.WechatConfigProperties;
import com.bus.ticket.constant.wx.WxGetUserInfoKey;
import com.bus.ticket.model.wx.UserLoginContext;
import com.bus.ticket.model.wx.WechatLoginResponse;
import com.bus.ticket.util.JSONUtils;

import cn.hutool.http.HttpUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信登录，获取OpenId等信息
 * 
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
@Slf4j
public class WechatAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final String GET_METHOD = "GET";

    private static final String LOGIN_CODE_PARAMETER = "code";

    private static final String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    private WechatConfigProperties wechatConfigProperties;

    public WechatAuthenticationProcessingFilter(WechatConfigProperties wechatConfigProperties) {
        super(new AntPathRequestMatcher("/auth/wechatLogin", GET_METHOD));
        this.wechatConfigProperties = wechatConfigProperties;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException, IOException, ServletException {
        if (!GET_METHOD.equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String iv = request.getParameter(WxGetUserInfoKey.IV);
        String rawData = request.getParameter(WxGetUserInfoKey.RAW_DATA);
        String signature = request.getParameter(WxGetUserInfoKey.SIGNATURE);
        String encryptedData = request.getParameter(WxGetUserInfoKey.ENCRYPTED_DATA);
        log.info("iv : {}", iv);
        log.info("RAW_DATA : {}", rawData);
        log.info("SIGNATURE : {}", signature);
        log.info("ENCRYPTED_DATA : {}", encryptedData);

        // 校验code
        String code = request.getParameter(LOGIN_CODE_PARAMETER);
        if (code == null) {
            throw new AuthenticationServiceException("code is empty");
        }
        // 发送登录请求
        WechatLoginResponse loginResponse = wechatLogin(code);

        UserLoginContext userContext = new UserLoginContext();
        userContext.setIv(iv);
        userContext.setRawData(rawData);
        userContext.setUserInfo(JSONUtils.parseIgnoreErrors(rawData, UserLoginContext.UserInfo.class));
        userContext.setSignature(signature);
        userContext.setEncryptedData(encryptedData);
        userContext.setOpenId(loginResponse.getOpenId());
        userContext.setSessionKey(loginResponse.getSessionKey());
        userContext.setUnionId(loginResponse.getUnionId());

        // 创建未认证token
        WechatAuthenticationToken authRequest = new WechatAuthenticationToken(userContext, null);
        // 验证token
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 微信登录返回用户openid
     */
    @SneakyThrows
    private WechatLoginResponse wechatLogin(String code) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("appid", wechatConfigProperties.getAppId());
        params.put("secret", wechatConfigProperties.getSecret());
        params.put("grant_type", "authorization_code");
        params.put("js_code", code);
        log.info("wechatLogin request : {}", params);
        String response = HttpUtil.get(LOGIN_URL, params);
        log.info("wechatLogin response  : {}", response);
        WechatLoginResponse responseObj = JSONUtils.parse(response, WechatLoginResponse.class);
        return Optional.of(responseObj).filter(obj -> obj.getErrCode() == null)
            .orElseThrow(() -> new AuthenticationServiceException("微信登录异常"));
    }

    @Autowired
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
