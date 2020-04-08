package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.CategoryMapper;
import com.yanrui.api.pojo.Category;
import com.yanrui.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-08 15:45
 **/
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> findAll() {
        return categoryMapper.findAllCategory();
    }
}
