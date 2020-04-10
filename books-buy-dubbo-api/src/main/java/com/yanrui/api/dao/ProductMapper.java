package com.yanrui.api.dao;

import com.yanrui.api.pojo.Product;

import java.util.List;
import java.util.Map;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Map<String,Object>> findAllProduct();

    List<Map<String,Object>> findAllProductPage(Integer id, Integer size);
}