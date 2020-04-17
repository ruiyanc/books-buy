package com.yanrui.comsumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yanrui
 * @description
 * @create: 2020-01-02 11:40
 */
@SpringBootApplication
@EnableDubbo
@MapperScan("com.yanrui.api.dao")
//@Configuration("com.yanrui.api.config")
public class BooksBuyDubboConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BooksBuyDubboConsumerApplication.class, args);
    }

}
