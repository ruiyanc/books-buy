package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.ProductMapper;
import com.yanrui.api.pojo.Product;
import com.yanrui.api.service.AdminService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
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

    @Override
    public int insertProduct(Map<String,Object> map) {
        Integer category = Integer.valueOf(map.get("category").toString());
        Map<String,Object> productForm = (Map<String, Object>) map.get("productForm");
        Product product = new Product();
        try {
            BeanUtils.populate(product, productForm);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        product.setId(null);
        product.setCategoryId(category);
        product.setStatus("1");
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        System.out.println(product.toString());
        int i = productMapper.insert(product);
        return i;
    }
}
