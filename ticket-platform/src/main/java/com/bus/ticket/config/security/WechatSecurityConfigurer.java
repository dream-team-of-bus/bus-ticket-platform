package com.bus.ticket.config.security;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import com.bus.ticket.config.wx.WechatConfigProperties;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
public class WechatSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Resource
    private WechatAuthenticationProvider wechatAuthenticationProvider;
    @Resource
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    private WechatConfigProperties wechatConfigProperties;

    public WechatSecurityConfigurer(WechatConfigProperties wechatConfigProperties) {
        this.wechatConfigProperties = wechatConfigProperties;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        WechatAuthenticationProcessingFilter filter = new WechatAuthenticationProcessingFilter(wechatConfigProperties);
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        // 获取已有的身份验证管理器
        AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
        if (authenticationManager != null) {
            filter.setAuthenticationManager(authenticationManager);
        }
        // 获取已有的会话身份验证策略
        SessionAuthenticationStrategy sessionAuthenticationStrategy =
            builder.getSharedObject(SessionAuthenticationStrategy.class);
        if (sessionAuthenticationStrategy != null) {
            filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
        }
        builder.authenticationProvider(wechatAuthenticationProvider);
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
