package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.OrderMapper;
import com.yanrui.api.pojo.Order;
import com.yanrui.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-24 17:20
 **/
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class OrderServiceImpl implements OrderService {

    private final String CANCEL = "0";
    private final String PAYMENT = "2";
    private final String FINISH = "3";

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int addOrder(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public List<Map<String, Object>> findAllOrderByUid(String uid) {
        return orderMapper.findAllOrderByUid(uid);
    }

    @Override
    public List<Map<String, Object>> findOrderByUidAndStatus(String uid, String status) {
        return orderMapper.findOrderByUidAndStatus(uid, status);
    }

    @Override
    public int orderCancel(Map<String,Object> map) {
        Order order = new Order();
        order.setId((Integer) map.get("orderId"));
        order.setUpdateTime(new Date());
        order.setStatus(CANCEL);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int orderPayment(Map<String, Object> map) {
        Order order = new Order();
        order.setId((Integer) map.get("orderId"));
        order.setUpdateTime(new Date());
        order.setPaymentTime(new Date());
        order.setStatus(PAYMENT);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

}
