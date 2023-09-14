package com.example.case6.service;

import com.example.case6.model.Order;

import java.util.List;

public interface IOrderService {
    Order update(Order order);
    Order save(Order order);
    List<Order> getByStatusAndCustomer(long statusId, long customerId);
    List<Order> getAllOrdersByCustomerId(long idCustomer);
    List<Order> getAllOrdersByShopId(long idShop);
    Order findById(long id);
    void deleteById(long idOrder);

}
