package com.yanrui.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Cart implements Serializable {
    private Integer id;

    private String uid;

    private Integer productId;

    private Integer quantity;

    private Date createTime;

    private Date updateTime;

}