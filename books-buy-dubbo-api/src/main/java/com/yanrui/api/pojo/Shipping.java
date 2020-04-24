package com.yanrui.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Shipping implements Serializable {
    private Integer id;

    private String uid;

    private String name;

    private String phone;

    private String address;

    private Date createTime;

    private Date updateTime;

}