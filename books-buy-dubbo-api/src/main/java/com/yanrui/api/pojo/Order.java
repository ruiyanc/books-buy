package com.yanrui.api.pojo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order implements Serializable {
    private Integer id;

    private String orderNo;

    private String uid;

    private Integer shippingId;

    private BigDecimal payment;

    private String paymentType;

    private Integer score;

    private String status;

    private Date paymentTime;

    private Date sendTime;

    private Date endTime;

    private Date closeTime;

    private Date createTime;

    private Date updateTime;

}