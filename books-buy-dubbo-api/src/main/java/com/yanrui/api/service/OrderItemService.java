package com.yanrui.api.service;

import com.yanrui.api.pojo.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderItemService {

    int addOrderItem(OrderItem orderItem);

    List<Map<String, Object>> findOrderItemsByOrderNo(String orderNo);
}
