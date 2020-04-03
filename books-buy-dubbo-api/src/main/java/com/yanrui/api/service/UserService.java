package com.yanrui.api.service;

import com.yanrui.api.pojo.User;

import java.util.Map;

public interface UserService {
    User findUsernameOrPhone(String usernameOrPhone);

    int addUser(Map<String,Object> map);
}
