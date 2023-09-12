package com.example.case6.service.impl;

import com.example.case6.model.Order;
import com.example.case6.repository.IOderRepo;
import com.example.case6.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    IOderRepo iOderRepo;
    @Override
    public Order save(Order order) {
        return iOderRepo.save(order);
    }
    @Override
    public List<Order> getByStatusAndCustomer(long statusId, long customerId) {
        return iOderRepo.getAllByStatusIdAndUserId(statusId, customerId);
    }
}
