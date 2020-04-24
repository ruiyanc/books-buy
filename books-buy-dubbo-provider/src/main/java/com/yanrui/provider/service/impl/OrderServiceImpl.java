package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.OrderMapper;
import com.yanrui.api.pojo.Order;
import com.yanrui.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-24 17:20
 **/
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int addOrder(Order order) {
        return orderMapper.insert(order);
    }
}
