package com.yanrui.api.utils;

import java.util.Random;

/**
 * @program: books-buy
 * @description: 生成唯一键
 * @author: yanrui
 * @create: 2020-04-03 16:46
 **/
public class KeyUtil {
    /**
     * 生成唯一的主键,当前时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        int number = random.nextInt(90000) + 10000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
