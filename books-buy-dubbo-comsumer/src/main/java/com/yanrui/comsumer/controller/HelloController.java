package com.yanrui.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yanrui.api.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yanrui
 * @description
 * @create: 2020-01-02 11:28
 */
@RestController
public class HelloController {

    @Reference(version = "${demo.service.version}")
    private HelloService helloService;

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name ) {
        return helloService.sayHello(name);
    }

}
