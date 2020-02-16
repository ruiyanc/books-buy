//package com.yanrui.comsumer.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.util.HtmlUtils;
//
//import java.util.Objects;
//
///**
// * @author: yanrui
// * @description
// * @create: 2020-01-09 14:24
// */
//@Controller
//public class LoginController {
//
//    @CrossOrigin
//    @PostMapping(value = "/api/login")
//    @ResponseBody
//    public String login(@RequestBody User requestUser) {
//        // 对 html 标签进行转义，防止 XSS 攻击
//        String username = requestUser.getUsername();
//        username = HtmlUtils.htmlEscape(username);
//
//        if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.getPassword())) {
//            String message = "账号密码错误";
//            System.out.println(message);
//            return "错误";
//        } else {
//            return "200";
//        }
//    }
//}
