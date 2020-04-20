package com.yanrui.api.service;

import java.util.List;
import java.util.Map;

public interface CommentService {
    List<Map<String,Object>> findCommentsByProductId(Integer productId);

    Double avgCommentsByProductId(Integer productId);

    int totalCommentsByProductId(Integer productId);
}
