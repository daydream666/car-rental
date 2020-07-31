package com.qf.service.impl;

import com.qf.entity.Order;
import com.qf.mapper.OrderMapper;
import com.qf.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 修改订单状态
     */
    @Override
    public void updateOrderStatus(Order order) {
        orderMapper.updateOrderStatus(order);
    }

    @Override
    public Integer countOrderStatus(Integer uid) {
        return orderMapper.countOrderStatus(uid);
    }
}
