package com.yanrui.api.service;

import com.yanrui.api.pojo.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    List<Map<String,Object>> findCommentsByProductId(Integer productId);

    Double avgCommentsByProductId(Integer productId);

    int totalCommentsByProductId(Integer productId);

    List<Map<String, Object>> findAllCommentProduct(String uid);

    int addComment(Map<String,Object> map);

    Comment findByUidAndProductId(String uid, Integer productId);
}
