package com.bus.ticket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.bus.ticket.dao")
@SpringBootApplication
public class TicketPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketPlatformApplication.class, args);
    }

}
