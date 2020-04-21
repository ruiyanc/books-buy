package com.yanrui.api.service;

import com.yanrui.api.pojo.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Map<String, Object>> findAllProductSpice();

    List<Map<String, Object>> findProductsByCategory(String category);

    List<Map<String, Object>> findProductByNewTime();
}
