package com.bus.ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.bus.ticket.config.wx.WechatConfigProperties;

/**
 * @author honglixiang
 */
@EnableConfigurationProperties(value = {WechatConfigProperties.class})
@MapperScan("com.bus.ticket.dao")
@SpringBootApplication
public class TicketPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketPlatformApplication.class, args);
    }

}
