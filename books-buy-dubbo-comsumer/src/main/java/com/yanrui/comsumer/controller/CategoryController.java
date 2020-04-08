package com.yanrui.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yanrui.api.pojo.Category;
import com.yanrui.api.service.CategoryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-08 15:30
 **/
@RestController
@RequestMapping(value = "api")
public class CategoryController {

    @Reference(version = "1.0.0")
    private CategoryService categoryService;

    @CrossOrigin
    @GetMapping(value = "findAllCategory")
    public List<Category> findAllCategory() {
        List<Category> list = categoryService.findAll();
        System.out.println(list);
        return list;
    }
}
