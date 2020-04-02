package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.UserMapper;
import com.yanrui.api.pojo.User;
import com.yanrui.api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yanrui
 */
@Service(version = "${demo.service.version}")
@org.springframework.stereotype.Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUsernameOrPhone(String usernameOrPhone) {
        User user = userMapper.queryUserByUsernameOrPhone(usernameOrPhone);
        return user;
    }
}
