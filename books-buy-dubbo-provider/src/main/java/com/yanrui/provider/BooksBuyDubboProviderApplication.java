package com.yanrui.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yanrui
 * @description
 * @create: 2020-01-02 11:40
 */
@EnableDubbo
@SpringBootApplication
@EnableCaching
@MapperScan("com.yanrui.api.dao")
//@Configuration("com.yanrui.api.config")
public class BooksBuyDubboProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BooksBuyDubboProviderApplication.class, args);
    }
}
