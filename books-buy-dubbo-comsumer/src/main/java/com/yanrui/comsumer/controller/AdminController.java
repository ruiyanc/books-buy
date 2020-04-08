package com.yanrui.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yanrui.api.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-07 16:59
 **/
@Slf4j
@RestController
@RequestMapping(value = "api")
public class AdminController {

    @Reference(version = "1.0.0")
    private AdminService adminService;

    @CrossOrigin
    @GetMapping(value = "adminFindAllProduct")
    public List<Map<String, Object>> adminFindAllProduct() {
        List<Map<String, Object>> list = adminService.findAllProduct();
        log.info("查询所有商品成功,{}", list);
        System.out.println(list);
        return list;
    }

    @CrossOrigin
    @PostMapping(value = "adminInsertProduct")
    public Map<String, Object> adminInsertProduct(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        Map<String, Object> hashMap = new HashMap<>();
        int i = adminService.insertProduct(map);
        if (i > 0) {
            hashMap.put("code", 200);
            hashMap.put("message", "注册成功");
        } else {
            String message = "信息填写不正确";
            hashMap.put("code", 400);
            hashMap.put("message", message);
        }
//        int
        return hashMap;
    }
}
