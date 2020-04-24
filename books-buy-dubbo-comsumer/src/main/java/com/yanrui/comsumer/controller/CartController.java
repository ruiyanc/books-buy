package com.yanrui.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yanrui.api.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-22 15:32
 **/
@Slf4j
@RestController
@RequestMapping(value = "api")
public class CartController {

    @Reference(version = "1.0.0")
    private CartService cartService;

    @CrossOrigin
    @PostMapping(value = "findProductCart")
    public List<Map<String, Object>> findProductCart(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        String uid = map.get("uid").toString();
        List<Map<String, Object>> list = cartService.findCartByUid(uid);
        log.info("该用户购物车为{}", list);
        return list;
    }

    //    添加商品到购物车
    @CrossOrigin
    @PostMapping(value = "addProductToCart")
    public Map<String, Object> addProductToCart(@RequestBody Map<String, Object> map) {
        Map<String, Object> hashMap = new HashMap<>();
        int i = cartService.insert(map);
        String message;
        if (i > 0) {
            message = "添加购物车成功！！";
            hashMap.put("code", 200);
            hashMap.put("message", message);
            log.info(message);
        } else {
            message = "添加购物车失败！！";
            hashMap.put("code", 400);
            hashMap.put("message", message);
            log.error(message);
        }
        return hashMap;
    }

//    购物车删除商品
    @CrossOrigin
    @PostMapping(value = "delProductToCart")
    public Map<String, Object> delProductToCart(@RequestBody Map<String, Object> map) {
        Map<String, Object> hashMap = new HashMap<>();
        Map<String,Object> cartMap = (Map<String, Object>) map.get("cart");
        System.out.println(cartMap);
        Integer cartId = Integer.valueOf(cartMap.get("cartId").toString());
        int i = cartService.delCartByPrimaryKey(cartId);
        if (i > 0) {
            log.info("购物车删除的商品为{}", cartMap);
            hashMap.put("code", 200);
        } else {
            hashMap.put("code", 400);
        }
        return hashMap;
    }
}
