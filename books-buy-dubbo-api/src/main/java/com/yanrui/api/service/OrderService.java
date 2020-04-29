package com.yanrui.api.service;

import com.yanrui.api.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    int addOrder(Order order);

    List<Map<String,Object>> findAllOrderByUid(String uid);

    List<Map<String,Object>> findOrderByUidAndStatus(String uid, String status);

    int orderCancel(Map<String,Object> map);

    int orderPayment(Map<String, Object> map);

}
