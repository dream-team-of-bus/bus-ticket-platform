package com.bus.ticket.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/7/27
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    boolean swaggerEnabled;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(apiInfo())
            // 是否开启swagger
            .enable(swaggerEnabled).select()
            // 过滤条件，扫描指定路径下的文件
            .apis(RequestHandlerSelectors.basePackage("com.bus.ticket.controller"))
            // 指定路径处理，PathSelectors.any()代表不过滤任何路径
            // .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("洪笠翔", "", "244313949@qq.com");
        return new ApiInfo("票务平台", "票务平台接口文档", "v1.0", "", contact, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }
}
