package com.yanrui.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yanrui.api.service.AdminService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-07 16:59
 **/
@RestController
@RequestMapping(value = "api")
public class AdminController {

    @Reference(version = "1.0.0")
    private AdminService adminService;

    @CrossOrigin
    @GetMapping(value = "adminFindAllProduct")
    public List<Map<String, Object>> adminFindAllProduct() {
        List<Map<String, Object>> list = adminService.findAllProduct();
        System.out.println(list);
        return list;
    }
}
