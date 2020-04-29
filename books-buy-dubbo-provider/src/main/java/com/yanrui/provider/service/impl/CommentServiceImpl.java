package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.CommentMapper;
import com.yanrui.api.pojo.Comment;
import com.yanrui.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
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

    @Override
    public List<Map<String, Object>> findAllCommentProduct(String uid) {
        return commentMapper.findAllCommentsByUid(uid);
    }

    @Override
    public int addComment(Map<String,Object> map) {
        Map<String,Object> productComment = (Map<String, Object>) map.get("productComment");
        Map<String,Object> orderItem = (Map<String, Object>) map.get("orderItem");
        String uid = orderItem.get("uid").toString();
        Integer productId = (Integer) orderItem.get("productId");
        if (commentMapper.selectByUidAndProductId(uid, productId) != null) {
            return 0;
        }
        Comment comment = new Comment();
        comment.setUid(uid);
        comment.setProductId(productId);
        comment.setCommentInfo(productComment.get("commentInfo").toString());
        comment.setScore((Integer) productComment.get("score"));
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        return commentMapper.insert(comment);
    }

    @Override
    public Comment findByUidAndProductId(String uid, Integer productId) {
        return commentMapper.selectByUidAndProductId(uid, productId);
    }
}
