package com.yanrui.api.dao;

import com.yanrui.api.pojo.Cart;

import java.util.List;
import java.util.Map;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<Map<String, Object>> findProductByUid(String uid);
}