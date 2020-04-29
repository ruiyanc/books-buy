package com.yanrui.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yanrui.api.pojo.Order;
import com.yanrui.api.pojo.OrderItem;
import com.yanrui.api.pojo.Product;
import com.yanrui.api.pojo.Shipping;
import com.yanrui.api.service.*;
import com.yanrui.api.utils.BeansUtil;
import com.yanrui.api.utils.OrderOnKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @Reference(version = "1.0.0")
    private ProductService productService;

    @Reference(version = "1.0.0")
    private CommentService commentService;



    //    首页单个购买
    @CrossOrigin
    @PostMapping(value = "addProductToOrder")
    public Map<String, Object> indexAddProductToOrder(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Map<String, Object> hashMap = new HashMap<>();
        Map<String, Object> userMap = (Map<String, Object>) map.get("user");
        Integer shippingId = addShipping(map);
        String orderOn = OrderOnKeyUtil.getUniqueKey();
        Integer quantity = Integer.valueOf(map.get("quantity").toString());
        String status = map.get("status").toString();
        Map<String, Object> productDetailMap = (Map<String, Object>) map.get("productDetails");
        System.out.println(productDetailMap);
        BigDecimal discountPrice = BigDecimal.valueOf(Double.parseDouble(productDetailMap.get("discountPrice").toString()));
        BigDecimal totalPrice = discountPrice.multiply(BigDecimal.valueOf(quantity));
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderNo(orderOn);
        orderItem.setUid(userMap.get("uid").toString());
        orderItem.setProductId((Integer) productDetailMap.get("id"));
        orderItem.setProductImage(productDetailMap.get("image").toString());
        orderItem.setProductName(productDetailMap.get("name").toString());
        orderItem.setCurrentUnitPrice(discountPrice);
        orderItem.setQuantity(quantity);
        orderItem.setTotalPrice(totalPrice);
        orderItem.setCreateTime(new Date());
        orderItem.setUpdateTime(new Date());
        //扣减商品库存
        int stock = (Integer) productDetailMap.get("stock") - quantity ;
        if (stock > 0) {
            orderItemService.addOrderItem(orderItem);
            log.info("添加到订单详情成功！");
            String message = "购买此商品成功！";
            hashMap.put("message", message);
            Product product = new Product();
            product.setId((Integer) productDetailMap.get("id"));
            product.setStock(stock);
            productService.updateProductByProductIdSelective(product);
            log.info("库存修改成功！");
        } else {
            String message = "商品库存不足，无法购买！";
            log.info(message);
            hashMap.put("code", 400);
            hashMap.put("message", message);
        }
        Order order = new Order();
        order.setOrderNo(orderOn);
        order.setShippingId(shippingId);
        order.setUid(userMap.get("uid").toString());
        order.setPayment(totalPrice);
        order.setPaymentType("1");
        order.setStatus(status);
        if (status.equals("2")) {
            order.setPaymentTime(new Date());
        }
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        int i1 = orderService.addOrder(order);
        if (i1 > 0) {
            log.info("订单添加成功！");
            hashMap.put("code", 200);
        }
        return hashMap;
    }


    @CrossOrigin
    @PostMapping(value = "cartAddProductToOrder")
    public Map<String, Object> cartAddProductToOrder(@RequestBody Map<String, Object> map) {
        Map<String, Object> hashMap = new HashMap<>();
        System.out.println(map);
        String orderOn = OrderOnKeyUtil.getUniqueKey();
        String uid = map.get("uid").toString();
        String status = map.get("status").toString();
        BigDecimal moneyTotal = BigDecimal.valueOf(Double.parseDouble(map.get("moneyTotal").toString()));
        Integer shippingId = addShipping(map);
        List<Map<String, Object>> productDetails = (List<Map<String, Object>>) map.get("productDetails");
        System.out.println(productDetails);
        for (Map<String,Object> productDetailMap : productDetails) {
            System.out.println(productDetailMap);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderNo(orderOn);
            orderItem.setUid(productDetailMap.get("uid").toString());
            orderItem.setProductId((Integer) productDetailMap.get("productId"));
            orderItem.setProductImage(productDetailMap.get("image").toString());
            orderItem.setProductName(productDetailMap.get("name").toString());
            orderItem.setCurrentUnitPrice(BigDecimal.valueOf(Double.parseDouble(productDetailMap.get("discountPrice").toString())));
            orderItem.setQuantity((Integer) productDetailMap.get("quantity"));
            orderItem.setTotalPrice(BigDecimal.valueOf(Double.parseDouble(productDetailMap.get("goodTotal").toString())));
            orderItem.setCreateTime(new Date());
            orderItem.setUpdateTime(new Date());
            int stock = (Integer) productDetailMap.get("stock") - (Integer) productDetailMap.get("quantity") ;
            if (stock > 0) {
                log.info("添加到订单详情成功！");
                Product product = new Product();
                product.setId((Integer) productDetailMap.get("productId"));
                product.setStock(stock);
                //修改库存
                productService.updateProductByProductIdSelective(product);
                int i = orderItemService.addOrderItem(orderItem);
                if (i > 0) {
                    log.info("库存修改成功！");
                }
            } else {
                log.info("{}商品库存不足！", productDetailMap.get("name"));
                hashMap.put("code", 400);
                hashMap.put("message", productDetailMap.get("name") + "库存不足");
                break;
            }
        }
        Order order = new Order();
        order.setOrderNo(orderOn);
        order.setShippingId(shippingId);
        order.setUid(uid);
        order.setPayment(moneyTotal);
        order.setPaymentType("1");
        order.setStatus(status);
        if (status.equals("2")) {
            order.setPaymentTime(new Date());
        }
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        int i1 = orderService.addOrder(order);
        if (i1 > 0) {
            log.info("订单添加成功！");
            hashMap.put("code", 200);
        }
        return null;
    }

    //用户查询全部订单
    @CrossOrigin
    @PostMapping(value = "findOrderInfo")
    public List<Map<String, Object>> findOrderInfo(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        String uid = map.get("uid").toString();
        List<Map<String, Object>> list = orderService.findAllOrderByUid(uid);
        log.info("此用户全部订单为{}", list);
        return list;
    }


    public Integer addShipping(Map<String, Object> map) {
        Map<String, Object> userMap = (Map<String, Object>) map.get("user");
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
        return shippingResult.getId();
    }

    @CrossOrigin
    @PostMapping(value = "findOrderItemByOrderNo")
    public List<Map<String, Object>> findOrderItemByOrderNo(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        String orderNo = map.get("orderNo").toString();
        List<Map<String, Object>> list = orderItemService.findOrderItemsByOrderNo(orderNo);
        log.info("该订单号详情商品为{}", list);
        return list;
    }

    @CrossOrigin
    @PostMapping(value = "findOrderByStatus")
    public List<Map<String, Object>> findOrderByStatus(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        String name = map.get("name").toString();
        String uid = map.get("uid").toString();
        List<Map<String, Object>> list;
        if (name.equals("0")) {
            list = orderService.findAllOrderByUid(uid);
        } else {
            list = orderService.findOrderByUidAndStatus(uid, name);
        }
        log.info("订单按状态查询为{}", list);
        return list;
    }

//    用户删除订单
    @CrossOrigin
    @PostMapping(value = "delOrder")
    public Map<String, Object> delOrder(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Map<String, Object> hashMap = new HashMap<>();
        Map<String,Object> orderMap = (Map<String, Object>) map.get("order");
        int i = orderService.orderCancel(orderMap);
        if (i > 0) {
            log.info("取消订单成功！");
            hashMap.put("code", 200);
            hashMap.put("message", "删除订单成功！");
        } else {
            hashMap.put("code", 400);
            hashMap.put("message", "删除失败，系统故障！");
        }
        return hashMap;
    }

    //    用户付款
    @CrossOrigin
    @PostMapping(value = "paymentOrder")
    public Map<String, Object> paymentOrder(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        Map<String, Object> hashMap = new HashMap<>();
        Map<String,Object> orderMap = (Map<String, Object>) map.get("order");
        int i = orderService.orderPayment(orderMap);
        if (i > 0) {
            log.info("订单付款成功！");
            hashMap.put("code", 200);
            hashMap.put("message", "订单付款成功！");
        }
        return hashMap;
    }

}
