package com.yanrui.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: books-buy
 * @description: 评价
 * @author: yanrui
 * @create: 2020-04-04 17:52
 **/
@Data
public class Comment implements Serializable {

    private Integer commentId;

    private String uid;

    private Integer productId;

    private String commentInfo;

    private Integer score;

    private Date createTime;

    private Date updateTime;
}
