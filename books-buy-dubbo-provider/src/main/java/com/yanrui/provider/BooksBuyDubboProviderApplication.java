package com.yanrui.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: yanrui
 * @description
 * @create: 2020-01-02 11:40
 */
@SpringBootApplication
@EnableDubbo
@EnableCaching
@MapperScan("com.yanrui.api.dao")
@EnableScheduling
public class BooksBuyDubboProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BooksBuyDubboProviderApplication.class, args);
    }
}
