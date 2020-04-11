package com.yanrui.api.service;

import com.yanrui.api.pojo.Product;
import com.yanrui.api.pojo.User;

import java.util.List;
import java.util.Map;

public interface AdminService {


    List<Map<String, Object>> findAllProduct();

    List<Map<String, Object>> findAllProductPage(Map<String, Object> map);

    int insertProduct(Map<String,Object> map);

    int updateProduct(Map<String, Object> map);

    int upOrDown(Map<String, Object> map);
}
