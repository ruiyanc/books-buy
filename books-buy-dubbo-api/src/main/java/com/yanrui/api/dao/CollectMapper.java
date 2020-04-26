package com.yanrui.api.dao;

import com.yanrui.api.pojo.Collect;

import java.util.List;
import java.util.Map;

public interface CollectMapper {

    int insert(Collect record);

    int deleteByPrimaryKey(Integer collectId);

    Collect selectByPrimaryKey(Integer id);

    int deleteByUidAndProductId(String uid, Integer productId);

    Collect selectByUidAndProductId(Integer productId, String uid);

    int findCountsByProductId(Integer productId);

    List<Map<String, Object>> findAllCollectByUid(String uid);
}
