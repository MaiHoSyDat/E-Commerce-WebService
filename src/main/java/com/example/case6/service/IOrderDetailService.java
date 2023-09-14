package com.example.case6.service;

import com.example.case6.model.Account;
import com.example.case6.model.OrderDetail;
import com.example.case6.model.dto.OrderDTO;

import java.util.List;

public interface IOrderDetailService {
    OrderDetail save(OrderDetail orderDetail);
    public void createOrderDetail(Account account);
    List<OrderDTO> getOrderByUnpaid(Account account);
    List<OrderDetail> getAllOrdersDetailByOrderId(long idOrder);
    void deleteByOrderId(long idOrder);
}
