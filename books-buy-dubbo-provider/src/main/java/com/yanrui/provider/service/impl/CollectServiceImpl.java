package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.CollectMapper;
import com.yanrui.api.pojo.Collect;
import com.yanrui.api.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-17 14:19
 **/
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public int addOrDeleteCollect(Map<String, Object> map) {
        Integer productId = Integer.valueOf(map.get("productId").toString());
        String uid = map.get("uid").toString();
        int i = collectMapper.deleteByUidAndProductId(uid, productId);
        if (i == 0) {
            Collect collect = new Collect();
            collect.setCollectId(null);
            collect.setProductId(productId);
            collect.setUid(uid);
            collect.setCreateTime(new Date());
            collect.setUpdateTime(new Date());
            return collectMapper.insert(collect);
        } else {
            return 0;
        }
    }

    @Override
    public int findCountByProductId(Integer productId) {
        return collectMapper.findCountsByProductId(productId);
    }

    @Override
    public Collect findCollectByUidAndProductId(Integer productId ,String uid) {
        return collectMapper.selectByUidAndProductId(productId, uid);
    }

    @Override
    public List<Map<String, Object>> findAllCollectProduct(String uid) {
        return collectMapper.findAllCollectByUid(uid);
    }
}
