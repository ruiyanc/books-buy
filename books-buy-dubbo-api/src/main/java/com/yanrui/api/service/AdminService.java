package com.yanrui.api.service;

import com.yanrui.api.pojo.Product;

import java.util.List;
import java.util.Map;

public interface AdminService {

    List<Map<String, Object>> findAllProduct();

    int insertProduct(Map<String,Object> map);
}
