package com.yanrui.api.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: books-buy
 * @description: 收藏
 * @author: yanrui
 * @create: 2020-04-04 17:51
 **/
@Data
public class Collect {

    private Integer collectId;

    private String uid;

    private Integer productId;

    private Date createTime;

    private Date updateTime;
}
