package com.cafebaby.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(value = "com.cafebaby")
@MapperScan(basePackages = "com.cafebaby.cafewechat.mapper")
public class CafeWechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeWechatApplication.class, args);
    }

}
