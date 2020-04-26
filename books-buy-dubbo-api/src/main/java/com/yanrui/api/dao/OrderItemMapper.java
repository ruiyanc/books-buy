package com.yanrui.api.dao;

import com.yanrui.api.pojo.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    List<Map<String,Object>> selectOrderItemByOrderNo(String orderNo);
}