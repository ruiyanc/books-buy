package com.yanrui.api.service;

import com.yanrui.api.pojo.Cart;

import java.util.List;
import java.util.Map;

public interface CartService {

    int insert(Map<String,Object> map);

    List<Map<String, Object>> findCartByUid(String uid);

    int delCartByPrimaryKey(Integer id);

    Cart selectCartByUidAndProductId(String uid, Integer productId);
}
