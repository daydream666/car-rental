package com.qf.service;

import com.qf.entity.Order;

public interface OrderService {
    public void updateOrderStatus(Order order);

    public Integer countOrderStatus(Integer uid);
}
