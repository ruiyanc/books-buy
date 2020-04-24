package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.CategoryMapper;
import com.yanrui.api.dao.ProductMapper;
import com.yanrui.api.pojo.Category;
import com.yanrui.api.pojo.Product;
import com.yanrui.api.pojo.User;
import com.yanrui.api.service.AdminService;
import com.yanrui.api.utils.BeansUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Map<String, Object>> findAllProduct() {
        return productMapper.findAllProduct();
    }

    @Override
    public List<Map<String, Object>> findAllProductPage(Map<String,Object> map) {
//        List<Map<String, Object>> list = productMapper.findAllProductPage();
//        return list;
        return null;
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
        return productMapper.insert(product);
    }

    @Override
    public int updateProduct(Map<String, Object> map) {
        Product product = new Product();
        System.out.println(map);
        BeansUtil.populate(product, map);
        Category category = categoryMapper.selectByName(map.get("category").toString());
        product.setCategoryId(category.getId());
        product.setUpdateTime(new Date());
        System.out.println(product.toString());
        return productMapper.updateByPrimaryKey(product);
    }

    @Override
    public int upOrDown(Map<String, Object> map) {
        Product product = new Product();
        String status = map.get("status").toString();
        Integer id = Integer.valueOf(map.get("id").toString());
        product.setId(id);
        String up = "1";
        if (up.equals(status)) {
            product.setStatus("2");
        } else {
            product.setStatus(up);
        }
        return productMapper.updateByPrimaryKeySelective(product);
    }

}
