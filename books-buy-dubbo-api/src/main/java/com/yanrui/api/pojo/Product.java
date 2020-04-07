package com.yanrui.api.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String author;

    private String subtitle;

    private String image;

    private String detail;

    private BigDecimal originalPrice;

    private BigDecimal discountPrice;

    private Integer stock;

    private String status;

    private Date createTime;

    private Date updateTime;

}