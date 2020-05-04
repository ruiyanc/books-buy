package com.yanrui.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yanrui.api.enums.ResultEnum;
import com.yanrui.api.pojo.User;
import com.yanrui.api.service.UserService;
import com.yanrui.api.utils.BeansUtil;
import com.yanrui.api.utils.StringToDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: yanrui
 * @description
 * @create: 2020-01-09 14:24
 */
@Slf4j
@RestController
@RequestMapping(value = "api")
public class UserController {

    @Reference(version = "${demo.service.version}")
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "login")
    public Map<String,Object> login(@RequestBody Map<String,Object> map) {
        // 对 html 标签进行转义，防止 XSS 攻击
//        String usernameOrPhone = HtmlUtils.htmlEscape(usernameOrPhone);
        Map<String,Object>  hashMap = new HashMap<>();
        String phoneOrUsername = map.get("phoneOrUsername").toString();
        String password = map.get("password").toString();
        User user = userService.findUsernameOrPhone(phoneOrUsername);
        if (user != null) {
            if (!Objects.equals(user.getPassword(), password)) {
                String message = ResultEnum.LOGIN_FAIL.getMessage();
                System.out.println(message);
                log.error("登录错误,{}", message);
                hashMap.put("message", message);
                hashMap.put("code", 400);
            } else {
                log.info("登录成功");
                hashMap.put("code", 200);
                hashMap.put("user", user);
            }
        } else {
            String message = ResultEnum.LOGIN_FAIL.getMessage();
            System.out.println(message);
            log.error("登录错误,{}", message);
            hashMap.put("message", message);
            hashMap.put("code", 400);
        }
        return hashMap;
    }

    @CrossOrigin
    @PostMapping(value = "register")
    public Map<String,Object> register(@RequestBody Map<String,Object> map) {
        Map<String, Object> hashMap = new HashMap<>();
        System.out.println(map);
        Map<String,Object> ruleForm =  (Map<String, Object>) map.get("ruleForm");
        String phone = ruleForm.get("phone").toString();
        String username = ruleForm.get("username").toString();
        if (userService.findUsernameOrPhone(phone) == null && userService.findUsernameOrPhone(username) == null) {
            int i = userService.addUser(ruleForm);
            if (i > 0) {
                log.info("注册成功!!");
                hashMap.put("code", 200);
                hashMap.put("message", "success");
            } else {
                String message = "注册失败";
                log.error("注册失败!数据库新增失败!");
                hashMap.put("code", 400);
                hashMap.put("message", message);
            }
        } else {
            hashMap.put("code", 400);
            hashMap.put("message", "用户名已存在或手机号已注册，请直接登录");
        }
        return hashMap;
    }

    @CrossOrigin
    @PostMapping(value = "updateUserInfo")
    public Map<String, Object> updateUserInfo(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Map<String, Object> hashMap = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        Map<String,Object> userMap = (Map<String, Object>) map.get("user");
        String time = StringToDateUtil.stringToDate(userMap.get("createTime").toString());
        Date createTime = new Date();
        try {
            createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
            calendar.setTime(createTime);
            calendar.add(Calendar.HOUR, 8);
            createTime = calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user = new User();
        BeansUtil.populate(user, userMap);
        user.setCreateTime(createTime);
        user.setUpdateTime(new Date());
        int i = userService.updateUserInfo(user);
        if (i > 0) {
            User user1 = userService.findUsernameOrPhone(user.getUsername());
            hashMap.put("code", 200);
            hashMap.put("user", user1);
            hashMap.put("message", "修改信息成功！");
            log.info("用户修改信息成功！");
        } else {
            hashMap.put("code", 400);
            hashMap.put("message", "修改信息失败！");
        }
        return hashMap;
    }
}
