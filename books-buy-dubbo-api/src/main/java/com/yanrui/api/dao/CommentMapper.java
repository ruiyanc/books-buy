package com.yanrui.api.dao;

import com.yanrui.api.pojo.Comment;

public interface CommentMapper {

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer id);
}
