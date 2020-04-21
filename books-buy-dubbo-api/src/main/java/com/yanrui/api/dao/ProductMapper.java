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

//    按打折的升序排列
    List<Map<String,Object>> findAllProductSpice(String status);

//    查询按类别分类的所有商品
    List<Map<String,Object>> findAllProductByCategory(String status, String category);

    List<Map<String,Object>> findProductByNewTime(String status);
}