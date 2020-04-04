package com.yanrui.api.dao;

import com.yanrui.api.pojo.Collect;

public interface CollectMapper {

    int insert(Collect record);

    Collect selectByPrimaryKey(Integer id);
}
