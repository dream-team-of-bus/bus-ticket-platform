package com.bus.ticket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.ticket.config.security.AuthenticationUserDetails;
import com.bus.ticket.entity.User;
import com.bus.ticket.model.security.UserSessionVo;
import com.bus.ticket.util.AuthenticationUtils;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
@RestController
public class AuthenticationController {

    @RequestMapping("/auth/session")
    public UserSessionVo getSession(HttpSession httpSession) {
        UserSessionVo vo = new UserSessionVo();
        vo.setSessionId(httpSession.getId());

        AuthenticationUserDetails au = AuthenticationUtils.getAuthenticationUserDetails();
        if (au != null) {
            User user = au.getUser();
            BeanUtils.copyProperties(user, vo);
            return vo;
        }
        return null;
    }

    @RequestMapping(path = "/auth/loginFail")
    public void loginFail(HttpServletRequest request) {
        AuthenticationException exception =
            (AuthenticationException)request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        try {
            if (exception instanceof DisabledException) {
                throw new AuthenticationServiceException("账号被禁用");
            } else if (exception instanceof UsernameNotFoundException) {
                throw new AuthenticationServiceException("账号找不到");
            } else if (exception instanceof BadCredentialsException) {
                throw new AuthenticationServiceException("账号密码错误");
            } else {
                throw new AuthenticationServiceException(exception.getMessage());
            }
        } catch (Exception e) {
            throw e;
        }
    }
}