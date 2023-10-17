package com.bus.ticket.config.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
@Configuration
public class WebSecurityConfig<S extends Session> extends WebSecurityConfigurerAdapter {

    @Resource
    private MobileCodeSecurityConfigurer mobileCodeSecurityConfigurer;
    @Resource
    private WechatSecurityConfigurer wechatSecurityConfigurer;
    @Resource
    private MobileCodeAuthenticationProvider mobileCodeAuthenticationProvider;
    @Resource
    private WechatAuthenticationProvider wechatAuthenticationProvider;
    @Resource
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Resource
    private SessionRegistry sessionRegistry;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(mobileCodeAuthenticationProvider)
            .authenticationProvider(wechatAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.headers().frameOptions().disable();
        // 关闭CSRF
        http.csrf().disable().exceptionHandling()
            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        List<String> authAntPatterns = new ArrayList<>();
        authAntPatterns.add("/static/**");
        authAntPatterns.add("/auth/**");

        /**
         * swagger相关路径放行
         */
        authAntPatterns.add("/swagger-ui/**");
        authAntPatterns.add("/v2/**");
        authAntPatterns.add("/v3/**");
        authAntPatterns.add("/swagger-resources/**");
        String[] authAntPatternsValue = authAntPatterns.toArray(new String[authAntPatterns.size()]);
        http.authorizeRequests().antMatchers(authAntPatternsValue).permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        // 配置FormLoginConfigurer
        http.formLogin().loginProcessingUrl("/auth/login").successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler);

        // 配置LogoutConfigurer
        http.logout().invalidateHttpSession(true).logoutUrl("/auth/logout")
            .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        // 配置SessionManagementConfigurer
        // maximumSessions： session并发控制，-1表示不限制
        // maxSessionsPreventsLogin： 默认false新会话会顶掉最老的会话。true 后面登录不了
        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false).sessionRegistry(sessionRegistry);

        // 启用自定义MobileCodeSecurityConfigurer
        http.apply(mobileCodeSecurityConfigurer);
        http.apply(wechatSecurityConfigurer);
    }
}
