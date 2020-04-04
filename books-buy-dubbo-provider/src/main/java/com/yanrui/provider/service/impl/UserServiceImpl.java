package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.UserMapper;
import com.yanrui.api.pojo.User;
import com.yanrui.api.service.UserService;
import com.yanrui.api.utils.KeyUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 * @author yanrui
 */
@Service(version = "${demo.service.version}")
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUsernameOrPhone(String usernameOrPhone) {
        return userMapper.queryUserByUsernameOrPhone(usernameOrPhone);
    }

    @Override
    public int addUser(Map<String,Object> map) {
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        user.setUid(KeyUtil.genUniqueKey());
        user.setRole("2");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userMapper.insert(user);
    }
}
