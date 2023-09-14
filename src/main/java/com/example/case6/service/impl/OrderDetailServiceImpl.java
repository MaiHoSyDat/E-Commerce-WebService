package com.example.case6.service.impl;

import com.example.case6.model.Order;
import com.example.case6.model.OrderDetail;
import com.example.case6.repository.IOderDetailRepo;
import com.example.case6.repository.IOderRepo;
import com.example.case6.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService{
    @Autowired
    IOderDetailRepo iOderDetailRepo;
    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return iOderDetailRepo.save(orderDetail);
    }

    @Override
    public List<OrderDetail> getAllOrdersDetailByOrderId(long idOrder) {
        return iOderDetailRepo.getAllOrdersDetailByOrderId(idOrder);
    }

    @Override
    public void deleteByOrderId(long idOrder) {
        iOderDetailRepo.deleteByOrderId(idOrder);
    }
}
