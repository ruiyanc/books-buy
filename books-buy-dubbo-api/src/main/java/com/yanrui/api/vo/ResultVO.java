package com.yanrui.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-02 19:06
 **/
@Data
public class ResultVO<T> implements Serializable {
    /**错误码*/
    private Integer code;
    /**提示信息*/
    private String msg;
    /**具体内容*/
    private T data;
}
