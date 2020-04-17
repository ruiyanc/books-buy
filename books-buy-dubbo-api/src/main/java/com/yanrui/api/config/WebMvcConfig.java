package com.yanrui.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-17 18:23
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /** cors跨域设置*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("**")
                .allowedOrigins("http://localhost:8080")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(30 * 1000);
    }
}
