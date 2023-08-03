package com.bus.ticket.config.wx;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
@ConfigurationProperties(prefix = "wechat")
@Data
public class WechatConfigProperties {

    private String appId;

    private String secret;

    private String loginUrl;
}
