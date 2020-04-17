package com.yanrui.api.service;

import com.yanrui.api.pojo.Collect;

import java.util.Map;

public interface CollectService {

    int addOrDeleteCollect(Map<String,Object> map);

    int findCountByProductId(Integer productId);

    Collect findCollectByUidAndProductId(Integer productId, String uid);
}
