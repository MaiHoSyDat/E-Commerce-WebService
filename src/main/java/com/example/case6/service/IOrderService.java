package com.example.case6.service;

import com.example.case6.model.Order;

import java.util.List;

public interface IOrderService {
    Order save(Order order);
    List<Order> getByStatusAndCustomer(long statusId, long customerId);
}
