package com.qf.mapper;

import com.qf.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    public void addOrder(Order order);

    public List<Order> findAllOrders(Integer uid,Integer page);

    public Integer countOrders(Integer uid);

    public void updateOrderStatus(Order order);

    public Integer countOrderStatus(Integer uid);
}
