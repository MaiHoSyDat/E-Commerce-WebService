package com.example.case6.service.impl;

import com.example.case6.model.Order;
import com.example.case6.repository.IOderRepo;
import com.example.case6.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    IOderRepo iOderRepo;
    @Override
    public Order save(Order order) {
        return iOderRepo.save(order);
    }

    @Override
    public List<Order> getAllOrdersByCustomerId(long idCustomer) {
        return iOderRepo.getAllOrdersByCustomerId(idCustomer);
    }

    @Override
    public Order findById(long id) {
        Optional<Order> optionalOrder = iOderRepo.findById(id);
        return optionalOrder.orElse(null);
    }

    @Override
    public void deleteById(long idOrder) {
        iOderRepo.deleteById(idOrder);
    }
}
