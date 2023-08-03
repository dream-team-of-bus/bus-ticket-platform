package com.bus.ticket.config.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.slf4j.Slf4j;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
@Slf4j
public class MobileCodeAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "mobile";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "code";

    private String mobileParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String codeParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;

    private static String POST_METHOD = HttpMethod.POST.toString();

    public MobileCodeAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/auth/mobileLogin", POST_METHOD));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException, IOException, ServletException {
        if (!POST_METHOD.equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String mobile = request.getParameter(mobileParameter);
        String code = request.getParameter(codeParameter);

        if (mobile == null) {
            mobile = "";
        }

        if (code == null) {
            code = "";
        }

        mobile = mobile.trim();

        MobileCodeAuthenticationToke authRequest = new MobileCodeAuthenticationToke(mobile, code);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
