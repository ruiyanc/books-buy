package com.yanrui.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-24 18:14
 **/
public class OrderOnKeyUtil {
    /**
     * 生成唯一的订单号当前时间
     * @return
     */
    public static String getUniqueKey() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = simpleDateFormat.format(new Date());
        Random random = new Random();
        String i = String.valueOf(random.nextInt(900000) + 100000);
        return format + i;
    }
}
