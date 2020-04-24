package com.yanrui.api.service;

import com.yanrui.api.pojo.Shipping;

import java.util.Map;

public interface ShippingService {
    int addShipping(Shipping shipping);

    Shipping selectByShipping(Shipping shipping);
}
