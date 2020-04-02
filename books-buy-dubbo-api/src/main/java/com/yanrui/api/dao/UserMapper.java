package com.yanrui.api.dao;

import com.yanrui.api.pojo.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User queryUserByUsernameOrPhone(String usernameOrPhone);
}