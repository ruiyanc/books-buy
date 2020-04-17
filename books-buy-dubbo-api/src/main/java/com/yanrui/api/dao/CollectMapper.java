package com.yanrui.api.dao;

import com.yanrui.api.pojo.Collect;

public interface CollectMapper {

    int insert(Collect record);

    int deleteByPrimaryKey(Integer collectId);

    int deleteByUidAndProductId(String uid, Integer productId);

    Collect selectByUidAndProductId(Integer productId, String uid);

    Collect selectByPrimaryKey(Integer id);

    int findCountsByProductId(Integer productId);
}
