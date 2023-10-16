package com.bus.ticket.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bus.ticket.config.security.AuthenticationUserDetails;
import com.bus.ticket.constant.UserTypeEnum;
import com.bus.ticket.entity.User;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
public class AuthenticationUtils {

    public static AuthenticationUserDetails getAuthenticationUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthenticationUserDetails) {
            return (AuthenticationUserDetails)principal;
        }
        return null;
    }

    public static User getUser() {
        AuthenticationUserDetails au = getAuthenticationUserDetails();
        if (au != null) {
            return au.getUser();
        }
        return null;
    }

    public static boolean isDriver() {
        User user = getUser();
        if (user == null) {
            return false;
        }
        return UserTypeEnum.DRIVER.getType() == user.getType();
    }
}
