package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.CartMapper;
import com.yanrui.api.pojo.Cart;
import com.yanrui.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-22 14:57
 **/
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;

    @Override
    public int insert(Map<String, Object> map) {
        String uid = map.get("uid").toString();
        Integer quantity = (Integer) map.get("quantity");
        if (quantity == null) {
            quantity = 1;
        }
        Integer productId = Integer.valueOf(map.get("productId").toString());
        if (cartMapper.selectCartByUidAndProductId(uid, productId) != null) {
            return 0;
        }
        Cart cart = new Cart();
        cart.setId(null);
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setUid(uid);
        cart.setCreateTime(new Date());
        cart.setUpdateTime(new Date());
        return cartMapper.insert(cart);
    }

    @Override
    public List<Map<String, Object>> findCartByUid(String uid) {
        return cartMapper.findProductByUid(uid);
    }

    @Override
    public int delCartByPrimaryKey(Integer id) {
        return cartMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Cart selectCartByUidAndProductId(String uid, Integer productId) {
        return cartMapper.selectCartByUidAndProductId(uid, productId);
    }
}
