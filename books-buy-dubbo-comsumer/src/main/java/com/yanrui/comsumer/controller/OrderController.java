package com.yanrui.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yanrui.api.pojo.Order;
import com.yanrui.api.pojo.OrderItem;
import com.yanrui.api.pojo.Shipping;
import com.yanrui.api.service.OrderItemService;
import com.yanrui.api.service.OrderService;
import com.yanrui.api.service.ShippingService;
import com.yanrui.api.utils.BeansUtil;
import com.yanrui.api.utils.OrderOnKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-22 13:30
 **/
@RestController
@Slf4j
@RequestMapping("api")
public class OrderController {

    @Reference(version = "1.0.0")
    private OrderService orderService;

    @Reference(version = "1.0.0")
    private OrderItemService orderItemService;

    @Reference(version = "1.0.0")
    private ShippingService shippingService;

    @CrossOrigin
    @PostMapping(value = "addProductToOrder")
    public Map<String, Object> addProductToOrder(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        Map<String,Object> userMap = (Map<String, Object>) map.get("user");
        String address = map.get("address").toString();
        Shipping shipping = new Shipping();
        BeansUtil.populate(shipping, userMap);
        shipping.setAddress(address);
        shipping.setCreateTime(new Date());
        shipping.setUpdateTime(new Date());
        if (userMap.get("username") != null) {
            shipping.setName(userMap.get("username").toString());
        }
        Shipping shippingResult = shippingService.selectByShipping(shipping);
        if (shippingResult == null) {
            shippingService.addShipping(shipping);
            log.info("添加收货人成功！");
            shippingResult = shippingService.selectByShipping(shipping);
        } else {
            log.info("该收货人已存在无须添加！");
        }
        Integer shippingId = shippingResult.getId();
        String orderOn = OrderOnKeyUtil.getUniqueKey();
        // todo
        Object productDetailsMap = map.get("productDetails").toString();
        System.out.println(productDetailsMap);
        Integer quantity = (Integer) map.get("quantity");
        String status = map.get("status").toString();
        Order order = new Order();
        OrderItem orderItem = new OrderItem();

        return null;
    }
}
