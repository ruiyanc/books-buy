package com.yanrui.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yanrui.api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
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
 * @create: 2020-04-16 15:39
 **/
@Slf4j
@RestController
@RequestMapping(value = "api")
public class ProductController {

    @Reference(version = "1.0.0")
    private ProductService productService;

    @CrossOrigin
    @GetMapping(value = "findAllProductSpice")
    public List<Map<String, Object>> findAllProductSpice() {
        List<Map<String, Object>> list = productService.findAllProductSpice();
        log.info("查询所有在售商品并按打折排序成功,{}", list);
        return list;
    }
}
