package com.yanrui.comsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.base.Strings;
import com.yanrui.api.pojo.Collect;
import com.yanrui.api.pojo.Comment;
import com.yanrui.api.service.CollectService;
import com.yanrui.api.service.CommentService;
import com.yanrui.api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
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

    @Reference(version = "1.0.0")
    private CollectService collectService;

    @Reference(version = "1.0.0")
    private CommentService commentService;

    @CrossOrigin
    @GetMapping(value = "findAllProductSpice")
    public List<Map<String, Object>> findAllProductSpice() {
        List<Map<String, Object>> list = productService.findAllProductSpice();
        log.info("查询所有在售商品并按打折排序成功,{}", list);
        return list;
    }

    @CrossOrigin
    @GetMapping(value = "findProductByOrderItem")
    public List<Map<String, Object>> findProductByOrderItem() {
        List<Map<String, Object>> list = productService.findProductByOrderItem();
        log.info("查询所有在售商品并按销量排序,{}", list);
        return list;
    }

    @CrossOrigin
    @GetMapping(value = "initProductAll")
    public List<Map<String, Object>> initProductAll() {
        List<Map<String, Object>> list = productService.initProductAll();
        log.info("查询所有在售商品成功,{}", list);
        return list;
    }

    @CrossOrigin
    @PostMapping(value = "addOrDeleteCollectByProductId")
    @CacheEvict(value = "anyThings", key = "'collect:'+#map.get('uid')")
    public Map<String, Object> addOrDeleteCollectByProductId(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Map<String, Object> hashMap = new HashMap<>();
        int i = collectService.addOrDeleteCollect(map);
        if (i > 0) {
            log.info("收藏商品成功！");
            hashMap.put("code", 200);
            hashMap.put("message", "收藏此商品成功！");
        } else {
            log.info("取消收藏成功！");
            hashMap.put("code", 200);
            hashMap.put("message", "取消收藏成功！");
        }
        return hashMap;
    }

    @CrossOrigin
    @PostMapping(value = "findCollectByProductId")
    public Map<String, Object> findCollectByProductId(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        Map<String, Object> hashMap = new HashMap<>();
        Integer productId = Integer.valueOf(map.get("productId").toString());
        String uid = map.get("uid").toString();
        int collectCounts = collectService.findCountByProductId(productId);
        Collect collect = null;
        if (!Strings.isNullOrEmpty(uid)) {
            collect = collectService.findCollectByUidAndProductId(productId, uid);
        }
        hashMap.put("code", 200);
        hashMap.put("collect", collect);
        hashMap.put("collectCounts", collectCounts);
        log.info("此商品收藏的人数为{}", collectCounts);
        return hashMap;
    }

    @CrossOrigin
    @PostMapping(value = "findCommentByProductId")
    public Map<String, Object> findCommentByProductId(@RequestBody Map<String, Object> map) {
        Integer productId = Integer.valueOf(map.get("productId").toString());
        Map<String, Object> hashMap = new HashMap<>();
        Double avgRate = commentService.avgCommentsByProductId(productId);
        List<Map<String, Object>> commentData = commentService.findCommentsByProductId(productId);
        int comments = commentService.totalCommentsByProductId(productId);
        log.info("此商品平均评分为{}", avgRate);
        log.info("此商品评论人数为{}", comments);
        hashMap.put("code", 200);
        hashMap.put("comments", comments);
        hashMap.put("commentData", commentData);
        hashMap.put("avgRate", avgRate);
        return hashMap;
    }

    /**
     * 普通用户根据条件查询在售商品
     */
    @CrossOrigin
    @PostMapping(value = "findProductByLabel")
    public Map<String, Object> findProductByLabel(@RequestBody Map<String, Object> map) {
        String name = map.get("name").toString();
        Map<String, Object> hashMap = new HashMap<>();
        List<Map<String, Object>> list;
        if (name.equals("0")) {
            list = productService.findProductByNewTime();
        } else {
            String label = map.get("label").toString();
            list = productService.findProductsByCategory(label);
        }
        log.info("按条件查询的商品为{}", list);
        hashMap.put("code", 200);
        hashMap.put("labelData", list);
        return hashMap;
    }

    @CrossOrigin
    @PostMapping(value = "findAnyThingsBy")
    @Cacheable(value = "anyThings", key = "#map.get('name')+':'+#map.get('uid')")
    public List<Map<String, Object>> findAnyThingsBy(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        String name = map.get("name").toString();
        String uid = map.get("uid").toString();
        List<Map<String, Object>> list = null;
        if (name.equals("collect")) {
            list = collectService.findAllCollectProduct(uid);
            for (Map<String,Object> objectMap: list) {
                Integer productId = (Integer) objectMap.get("productId");
                int count = collectService.findCountByProductId(productId);
                objectMap.put("collects", count);
                int i = commentService.totalCommentsByProductId(productId);
                objectMap.put("comments", i);
            }
            log.info("当前用户的收藏商品为{}", list);
        } else if (name.equals("comment")) {
            list = commentService.findAllCommentProduct(uid);
            log.info("当前用户评论了的商品为{}", list);
        }
        return list;
    }

    @CrossOrigin
    @PostMapping(value = "addProductComment")
    @CacheEvict(value = "anyThings", key = "'comment:'+#map.get('uid')")
    public Map<String, Object> addProductComment(@RequestBody Map<String,Object> map) {
        System.out.println(map);
        Map<String, Object> hashMap = new HashMap<>();
        int i = commentService.addComment(map);
        if (i > 0) {
            log.info("添加评价成功！");
            hashMap.put("code", 200);
            hashMap.put("message", "评价该商品成功！");
        } else {
            hashMap.put("code", 400);
            hashMap.put("message", "此商品已评价，无须重复评价！");
        }
        return hashMap;
    }


}
