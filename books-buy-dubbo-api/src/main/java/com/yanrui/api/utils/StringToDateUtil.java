package com.yanrui.api.utils;

import java.util.regex.Pattern;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-10 15:59
 **/
public class StringToDateUtil {
//    时间格式化为yyyy-MM-dd HH:mm:ss
    public static String stringToDate(String s) {
        String replace = s.replace("T", " ");
        String regEx = "\\..*";
        Pattern pattern = Pattern.compile(regEx);
        String date = pattern.matcher(replace).replaceAll("");
        System.out.println(date);
        return date;
    }
}
