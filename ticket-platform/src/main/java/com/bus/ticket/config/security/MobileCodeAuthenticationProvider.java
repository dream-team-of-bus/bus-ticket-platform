package com.bus.ticket.config.security;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bus.ticket.entity.User;
import com.bus.ticket.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
@Slf4j
public class MobileCodeAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String mobile = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        String code = (String)authentication.getCredentials();

        if (StringUtils.isBlank(mobile)) {
            throw new UsernameNotFoundException(mobile);
        }

        // if (StringUtils.isBlank(code)) {
        // throw new VerificationCodeException(code);
        // }
        //
        // boolean check = verificationCodeService.checkVerificationCode(mobile, code);
        // if (!check) {
        // throw new VerificationCodeException(code);
        // } else {
        // verificationCodeService.clearVerificationCode(mobile);
        // }

        User user = userService.getByMobile(mobile);
        // if (user == null) {
        // user = addUser(mobile);
        // }

        AuthenticationUserDetails authenticationUserDetails = new AuthenticationUserDetails(user);

        MobileCodeAuthenticationToke authenticationToke =
            new MobileCodeAuthenticationToke(authenticationUserDetails, null, null);
        authenticationToke.setDetails(authentication.getDetails());
        return authenticationToke;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileCodeAuthenticationToke.class.isAssignableFrom(authentication);
    }
}
