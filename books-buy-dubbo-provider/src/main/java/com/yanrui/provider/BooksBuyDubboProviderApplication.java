package com.yanrui.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: yanrui
 * @description
 * @create: 2020-01-02 11:40
 */
@EnableDubbo
@SpringBootApplication
public class BooksBuyDubboProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BooksBuyDubboProviderApplication.class, args);
    }
}
