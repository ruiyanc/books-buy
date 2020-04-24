package com.yanrui.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanrui.api.dao.ShippingMapper;
import com.yanrui.api.pojo.Shipping;
import com.yanrui.api.service.ShippingService;
import com.yanrui.api.utils.BeansUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * @program: books-buy
 * @description:
 * @author: yanrui
 * @create: 2020-04-24 17:18
 **/
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public int addShipping(Shipping shipping) {
        return shippingMapper.insert(shipping);
    }

    @Override
    public Shipping selectByShipping(Shipping shipping) {
        String name = shipping.getName();
        String phone = shipping.getPhone();
        String address = shipping.getAddress();
        return shippingMapper.selectByNamePhoneAndAddress(name, phone, address);
    }
}
