package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.ProductMapper;
import com.yanrui.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-16 15:38
 **/
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class ProductServiceImpl implements ProductService {
    private static final String UP ="1";

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Map<String, Object>> findAllProductSpice() {
        return productMapper.findAllProductSpice(UP);
    }

    @Override
    public List<Map<String, Object>> findProductsByCategory(String category) {
        return productMapper.findAllProductByCategory(UP, category);
    }

    @Override
    public List<Map<String, Object>> findProductByNewTime() {
        return productMapper.findProductByNewTime(UP);
    }
}
