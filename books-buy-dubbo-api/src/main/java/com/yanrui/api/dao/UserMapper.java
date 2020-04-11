package com.yanrui.api.dao;

import com.yanrui.api.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();

    int insert(User record);

    int insertSelective(User record);

    int updateBySelective(User record);

    User queryUserByUsernameOrPhone(String usernameOrPhone);
}