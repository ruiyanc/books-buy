package com.yanrui.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yanrui.api.enums.ResultEnum;
import com.yanrui.api.pojo.Product;
import com.yanrui.api.pojo.User;
import com.yanrui.api.service.AdminService;
import com.yanrui.api.service.UserService;
import com.yanrui.api.utils.StringToDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Reference(version = "1.0.0")
    private UserService userService;

    @CrossOrigin
    @GetMapping(value = "adminFindAllUser")
    public List<User> adminFindAllUser() {
        List<User> list = userService.findAll();
        log.info("查询所有用户成功,{}", list);
        return list;
    }

    @CrossOrigin
    @PostMapping(value = "adminUpdateUser")
    public Map<String, Object> adminUpdateUser(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        Map<String,Object> userMap = (Map<String, Object>) map.get("user");
        Map<String, Object> hashMap = new HashMap<>();
        int i = userService.updateUserByRole(userMap);
        return getStringObjectMap(hashMap, i);
    }

//    管理员查询所有商品
    @CrossOrigin
    @GetMapping(value = "adminFindAllProduct")
    public List<Map<String, Object>> adminFindAllProduct() {
        List<Map<String, Object>> list = adminService.findAllProduct();
        log.info("查询所有商品成功,{}", list);
        return list;
    }

    @CrossOrigin
    @PostMapping(value = "adminInsertProduct")
    public Map<String, Object> adminInsertProduct(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        Map<String, Object> hashMap = new HashMap<>();
        int i = adminService.insertProduct(map);
        return getStringObjectMap(hashMap, i);
    }

    @CrossOrigin
    @PostMapping(value = "adminUpdateProduct")
    public Map<String, Object> adminUpdateProduct(@RequestBody Map<String, Object> map) {
        Map<String,Object> productFormInfo = (Map<String, Object>) map.get("productFormInfo");
        Map<String, Object> hashMap = new HashMap<>();
        System.out.println(productFormInfo);
        int i = adminService.updateProduct(productFormInfo);
        return getStringObjectMap(hashMap, i);
    }

    @CrossOrigin
    @PostMapping(value = "adminUpOrDown")
    public Map<String, Object> adminUpOrDown(@RequestBody Map<String, Object> map) {
        Map<String,Object> productFormInfo = (Map<String, Object>) map.get("productFormInfo");
        Map<String, Object> hashMap = new HashMap<>();
        System.out.println(productFormInfo);
        int i = adminService.upOrDown(productFormInfo);
        return getStringObjectMap(hashMap, i);
    }

    private Map<String, Object> getStringObjectMap(Map<String, Object> hashMap, int i) {
        if (i > 0) {
            hashMap.put("code", ResultEnum.SUCCESS.getCode());
            hashMap.put("message", ResultEnum.SUCCESS.getMessage());
            log.info("成功");
        } else {
            hashMap.put("code", ResultEnum.ERROR.getCode());
            hashMap.put("message", ResultEnum.ERROR.getMessage());
            log.error("输入信息发生错误,{}", ResultEnum.ERROR.getMessage());
        }
        return hashMap;
    }
}
