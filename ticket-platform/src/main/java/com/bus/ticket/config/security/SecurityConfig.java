package com.bus.ticket.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.bus.ticket.config.wx.WechatConfigProperties;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
@Configuration
public class SecurityConfig {

    public final String successForwardUrl = "/auth/session";
    public final String failureForwardUrl = "/auth/loginFail";

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    /**
     * session注册管理器
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public MobileCodeAuthenticationProvider mobileCodeAuthenticationProvider() {
        return new MobileCodeAuthenticationProvider();
    }

    @Bean
    public WechatSecurityConfigurer wechatSecurityConfigurer(WechatConfigProperties properties) {
        return new WechatSecurityConfigurer(properties);
    }

    @Bean
    public WechatAuthenticationProvider wechatAuthenticationProvider() {
        return new WechatAuthenticationProvider();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new ForwardAuthenticationSuccessHandler(successForwardUrl);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new ForwardAuthenticationFailureHandler(failureForwardUrl);
    }
}
