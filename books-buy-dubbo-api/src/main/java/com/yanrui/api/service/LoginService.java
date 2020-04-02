package com.yanrui.api.service;

import com.yanrui.api.pojo.User;

public interface LoginService {
    User findUsernameOrPhone(String usernameOrPhone);
}
