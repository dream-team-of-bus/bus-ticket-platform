package com.bus.ticket;

import java.util.Collections;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/3
 */
public class MySQLGeneratorTest {

    private static String DB_USERNAME = "root";

    private static String DB_PASSWORD = "hlx.abc.321.!@#";

    private static String TABLE_NAME = "bus_line_wait_point";

    /**
     * 配置参考地址：https://baomidou.com/pages/981406
     * 
     * @param args
     */
    public static void main(String[] args) {
        FastAutoGenerator
            .create(
                "jdbc:mysql://bus-llc.online:3306/ticket-platform?characterEncoding=UTF-8&useUnicode=true&useSSL=false",
                DB_USERNAME, DB_PASSWORD)
            // 全局配置
            .globalConfig(builder -> {
                builder.author("honglixiang") // 设置作者
                    .commentDate("yyyy-MM-dd hh:mm:ss") // 注释日期
                    .enableSwagger() // 开启Swagger注释
                    .outputDir("D:/Tidu/bus-ticket-platform/ticket-platform/src/main/java") // 指定输出目录
                    .disableOpenDir(); // 禁止打开输出目录，默认打开
            })
            // 包配置
            .packageConfig(builder -> {
                builder.parent("com.bus.ticket") // 设置父包名
                    .mapper("dao").pathInfo(Collections.singletonMap(OutputFile.xml,
                        "D:/Tidu/bus-ticket-platform/ticket-platform/src/main/resources/mapper")); // 设置mapperXml生成路径
            })
            // 策略配置
            .strategyConfig(builder -> {
                builder.addInclude(TABLE_NAME) // 设置需要生成的表名
                    // .addTablePrefix("sys_") // 设置过滤表前缀
                    // Entity 策略配置
                    .entityBuilder().enableLombok().fileOverride() // 覆盖已生成文件
                    .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略：下划线转驼峰命
                    .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略：下划线转驼峰命
                    // Mapper 策略配置
                    .mapperBuilder().fileOverride()
                    // Service 策略配置
                    .serviceBuilder().fileOverride() // 覆盖已生成文件
                    .formatServiceFileName("%sService") // 格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                    .formatServiceImplFileName("%sServiceImpl") // 格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl
                    // Controller 策略配置
                    .controllerBuilder().enableRestStyle().fileOverride() // 覆盖已生成文件
                ;
            }).templateConfig(builder -> {
                builder.controller("templates/controller.java.vm");
            }).execute();
    }
}
