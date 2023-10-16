package com.bus.ticket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bus.ticket.config.wx.WechatConfigProperties;
import com.bus.ticket.service.wx.AccessTokenManager;
import com.bus.ticket.service.wx.WxApiService;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/10/13
 */
@Configuration
public class CommonConfig {

    @Bean
    public AccessTokenManager accessTokenManager(WechatConfigProperties properties, WxApiService apiService) {
        return new AccessTokenManager(properties, apiService);
    }
}
