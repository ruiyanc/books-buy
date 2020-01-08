package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.service.HelloService;

/**
 * @author: yanrui
 * @description
 * @create: 2020-01-02 11:42
 */
@Service(version = "${demo.service.version}")
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "hello" + name;
    }
}
