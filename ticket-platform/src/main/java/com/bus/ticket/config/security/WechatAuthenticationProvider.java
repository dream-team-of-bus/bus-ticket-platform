package com.bus.ticket.config.security;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.bus.ticket.constant.UserTypeEnum;
import com.bus.ticket.entity.User;
import com.bus.ticket.service.UserService;

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
        log.info("{}", authentication);
        // 获取openId
        String openId = authentication.getName();
        // 根据openId查询用户
        User user = userService.getByOpenId(openId);
        // 用户存在则获取，不存在则注册
        if (user == null) {
            user = new User();
            user.setWxOpenId(openId);
            // user.setName();
            // user.setNickName();
            // user.setPhone();
            user.setType((int)UserTypeEnum.PASSENGER.getType());
            userService.insert(user);
        }
        AuthenticationUserDetails userDetails = new AuthenticationUserDetails(user);
        WechatAuthenticationToken authenticationToken = new WechatAuthenticationToken(userDetails, null, null);
        authenticationToken.setDetails(authentication.getDetails());
        return authenticationToken;
    }
}
