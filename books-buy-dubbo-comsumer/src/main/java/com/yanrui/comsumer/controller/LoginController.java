package com.yanrui.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yanrui.api.pojo.User;
import com.yanrui.api.service.LoginService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: yanrui
 * @description
 * @create: 2020-01-09 14:24
 */
@RestController
@RequestMapping(value = "api")
public class LoginController {

    @Reference(version = "${demo.service.version}")
    private LoginService loginService;
    @CrossOrigin
    @PostMapping(value = "login")
    public Map login(@RequestBody Map map) {
        // 对 html 标签进行转义，防止 XSS 攻击
//        String usernameOrPhone = HtmlUtils.htmlEscape(usernameOrPhone);
        Map<String,Object>  hashMap = new HashMap<>();
        String phoneOrUsername = map.get("phoneOrUsername").toString();
        String password = map.get("password").toString();
        User user = loginService.findUsernameOrPhone(phoneOrUsername);
        if (user != null) {
            if (!Objects.equals(user.getPassword(), password)) {
                String message = "账号不存在或密码错误";
                System.out.println(message);
                hashMap.put("message", message);
                hashMap.put("code", 400);
            } else {
                hashMap.put("username", user.getUsername());
                hashMap.put("code", 200);
            }
        }
        return hashMap;
    }
}
