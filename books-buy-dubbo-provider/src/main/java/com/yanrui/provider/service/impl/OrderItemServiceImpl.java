package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.OrderItemMapper;
import com.yanrui.api.pojo.OrderItem;
import com.yanrui.api.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-24 17:22
 **/

@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public int addOrderItem(OrderItem orderItem) {
        return orderItemMapper.insert(orderItem);
    }

    @Override
    public List<Map<String, Object>> findOrderItemsByOrderNo(String orderNo) {
        return orderItemMapper.selectOrderItemByOrderNo(orderNo);
    }
}
