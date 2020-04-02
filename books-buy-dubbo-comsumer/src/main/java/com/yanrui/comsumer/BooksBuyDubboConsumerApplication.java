package com.yanrui.comsumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.yanrui.api.dao")
public class BooksBuyDubboConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BooksBuyDubboConsumerApplication.class, args);
    }

}
