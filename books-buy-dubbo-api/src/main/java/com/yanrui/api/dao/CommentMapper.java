package com.yanrui.api.dao;

import com.yanrui.api.pojo.Comment;

import java.util.List;
import java.util.Map;

public interface CommentMapper {

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer id);

    List<Map<String, Object>> findCommentAndUserByProductId(Integer productId);

    Double findAvgCommentsByProductId(Integer productId);

    int findCommentsByProductId(Integer productId);
}
