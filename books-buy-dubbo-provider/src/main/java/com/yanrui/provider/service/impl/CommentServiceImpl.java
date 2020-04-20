package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.CommentMapper;
import com.yanrui.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-20 15:51
 **/
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Map<String, Object>> findCommentsByProductId(Integer productId) {
        return commentMapper.findCommentAndUserByProductId(productId);
    }

    @Override
    public Double avgCommentsByProductId(Integer productId) {
        return commentMapper.findAvgCommentsByProductId(productId);
    }

    @Override
    public int totalCommentsByProductId(Integer productId) {
        return commentMapper.findCommentsByProductId(productId);
    }
}
