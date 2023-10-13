package com.bus.ticket.service.wx.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;

import com.bus.ticket.constant.WxApiUrl;
import com.bus.ticket.model.wx.CheckSessionKeyResponse;
import com.bus.ticket.model.wx.GetAccessTokenResponse;
import com.bus.ticket.model.wx.GetPhoneResponse;
import com.bus.ticket.model.wx.OcrIdCardResponse;
import com.bus.ticket.model.wx.WechatLoginResponse;
import com.bus.ticket.service.wx.WxApiService;
import com.bus.ticket.util.JSONUtils;
import com.bus.ticket.util.WxUtil;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/12
 */
@Slf4j
@Service
public class WxApiServiceImpl implements WxApiService {

    @Override
    public WechatLoginResponse login(String appId, String secret, String code) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("appid", appId);
        params.put("secret", secret);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");

        log.debug("login request : {}", params);
        String response = HttpUtil.get(WxApiUrl.LOGIN, params);
        log.debug("login response  : {}", response);
        WechatLoginResponse responseObj = JSONUtils.parseIgnoreErrors(response, WechatLoginResponse.class);
        return Optional.of(responseObj).filter(obj -> obj.getErrCode() == null)
            .orElseThrow(() -> new AuthenticationServiceException("微信登录异常"));
    }

    @Override
    public GetAccessTokenResponse getToken(String appId, String secret) {
        Assert.notBlank(appId, "appId不能为空");
        Assert.notBlank(secret, "secret不能为空");

        Map<String, Object> params = new HashMap<>(3);
        params.put("appid", appId);
        params.put("secret", secret);
        params.put("grant_type", "client_credential");
        log.debug("getToken request params: {}", params);
        String response = HttpUtil.get(WxApiUrl.GET_TOKEN, params);
        log.debug("getToken response : {}", response);
        return JSONUtils.parseIgnoreErrors(response, GetAccessTokenResponse.class);
    }

    @Override
    public CheckSessionKeyResponse checkSessionKey(String token, String openId, String signature) {
        Assert.notBlank(token, "token不能为空");
        Assert.notBlank(openId, "openId不能为空");
        Assert.notBlank(signature, "signature不能为空");

        Map<String, Object> params = new HashMap<>(4);
        params.put("access_token", token);
        params.put("openid", openId);
        params.put("signature", signature);
        params.put("sig_method", "hmac_sha256");
        log.debug("checkSessionKey request params: {}", params);
        String response = HttpUtil.get(WxApiUrl.CHECK_SESSION, params);
        log.debug("checkSessionKey response : {}", response);
        CheckSessionKeyResponse responseObj = JSONUtils.parseIgnoreErrors(response, CheckSessionKeyResponse.class);
        return WxUtil.checkGet(responseObj);
    }

    @Override
    public GetPhoneResponse getPhoneNumber(String token, String code) {
        Assert.notBlank(token, "token不能为空");
        Assert.notBlank(code, "code不能为空");

        Map<String, Object> params = new HashMap<>(2);
        params.put("access_token", token);
        params.put("code", code);
        log.debug("getPhoneNumber request params: {}", params);
        String response = HttpUtil.post(WxApiUrl.GET_PHONE, params);
        log.debug("getPhoneNumber response : {}", response);
        GetPhoneResponse getPhoneResponse = JSONUtils.parseIgnoreErrors(response, GetPhoneResponse.class);
        return WxUtil.checkGet(getPhoneResponse);
    }

    @Override
    public OcrIdCardResponse ocrIdCard(String token, String imgUrl) {
        Assert.notBlank(token, "token不能为空");
        Assert.notBlank(imgUrl, "imgUrl不能为空");
        Map<String, Object> params = new HashMap<>(1);
        params.put("img_url", imgUrl);
        String url = StringUtils.join(WxApiUrl.OCR_ID_CARD, "?access_token=", token);
        log.debug("ocrIdCard request params: {}", params);
        String response = HttpUtil.post(url, params);
        log.debug("ocrIdCard response : {}", response);
        OcrIdCardResponse responseObj = JSONUtils.parseIgnoreErrors(response, OcrIdCardResponse.class);
        return WxUtil.checkGet(responseObj);
    }
}
