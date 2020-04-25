package com.yanrui.api.dao;

import com.yanrui.api.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Map<String, Object>> findAllOrderByUid(String uid);
}