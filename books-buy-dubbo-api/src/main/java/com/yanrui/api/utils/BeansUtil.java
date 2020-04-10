package com.yanrui.api.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-09 19:48
 **/
public class BeansUtil extends BeanUtils {
    public static void populate(Object bean, Map<String, ? extends Object> properties){
        try {
            //注册格式
            ConvertUtils.register(new Converter() {
                @Override
                public Object convert(Class type, Object value) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    try {
                        return simpleDateFormat.parse(StringToDateUtil.stringToDate(value.toString()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }, Date.class);
            //封装数据
            try {
                BeanUtils.populate(bean, properties);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
