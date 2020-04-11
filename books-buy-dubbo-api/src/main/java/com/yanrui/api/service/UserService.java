package com.yanrui.api.service;

import com.yanrui.api.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User findUsernameOrPhone(String usernameOrPhone);

    int addUser(Map<String,Object> map);

    List<User> findAll();

    int updateUserByRole(Map<String,Object> map);
}
