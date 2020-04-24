package com.yanrui.api.dao;

import com.yanrui.api.pojo.Shipping;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

//    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    Shipping selectByNamePhoneAndAddress(String name, String phone, String address);

//    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);


}