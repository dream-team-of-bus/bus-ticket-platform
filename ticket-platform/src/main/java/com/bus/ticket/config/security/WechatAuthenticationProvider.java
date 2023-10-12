package com.bus.ticket.config.security;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.bus.ticket.constant.UserTypeEnum;
import com.bus.ticket.entity.User;
import com.bus.ticket.model.wx.WxLoginUserContext;
import com.bus.ticket.service.UserService;
import com.bus.ticket.util.JSONUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
@Slf4j
public class WechatAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserService userService;

    @Override
    public boolean supports(Class<?> authentication) {
        return WechatAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WxLoginUserContext principal = (WxLoginUserContext)authentication.getPrincipal();
        log.info("{}", JSONUtils.toJSONStringIgnoreErrors(principal));
        // 获取openId
        String openId = principal.getOpenId();
        // 根据openId查询用户
        User user = userService.getByOpenId(openId);
        log.info("user : {}", JSONUtils.toJSONStringIgnoreErrors(user));
        // 用户存在则获取，不存在则注册
        if (user == null) {
            user = new User();
            user.setWxOpenId(openId);
            user.setName(principal.getUserInfo().getNickName());
            user.setNickName(principal.getUserInfo().getNickName());
            user.setGender(principal.getUserInfo().getGender());
            user.setHeadUrl(principal.getUserInfo().getAvatarUrl());
            user.setType((int)UserTypeEnum.PASSENGER.getType());
            userService.save(user);
        } else {
            /**
             * 修改用户登录时间
             */
            user.setNickName(principal.getUserInfo().getNickName());
            user.setGender(principal.getUserInfo().getGender());
            user.setHeadUrl(principal.getUserInfo().getAvatarUrl());

            User updater = new User();
            updater.setId(user.getId());
            updater.setNickName(user.getNickName());
            updater.setHeadUrl(user.getHeadUrl());
            updater.setLastLoginTime(new Date());
            userService.updateById(updater);
        }
        AuthenticationUserDetails userDetails = new AuthenticationUserDetails(user);
        userDetails.setSessionKey(principal.getSessionKey());
        WechatAuthenticationToken authenticationToken = new WechatAuthenticationToken(userDetails, null, null);
        authenticationToken.setDetails(authentication.getDetails());
        return authenticationToken;
    }
}
