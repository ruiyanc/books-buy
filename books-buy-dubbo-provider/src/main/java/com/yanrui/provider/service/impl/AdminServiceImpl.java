package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.ProductMapper;
import com.yanrui.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-07 17:20
 **/
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Map<String, Object>> findAllProduct() {
        List<Map<String, Object>> list = productMapper.findAllProduct();
        return list;
    }
}
