package com.example.case6.service.impl;

import com.example.case6.model.Order;
import com.example.case6.model.OrderDetail;
import com.example.case6.model.Product;
import com.example.case6.repository.IOderDetailRepo;
import com.example.case6.repository.IOderRepo;
import com.example.case6.repository.IProductRepo;
import com.example.case6.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    IOderRepo iOderRepo;
    @Autowired
    IProductRepo iProductRepo;
    @Autowired
    IOderDetailRepo iOderDetailRepo;

    @Override
    public Order update(Order order) {
        if (order.getStatus().getId() == 7){
            iOderRepo.save(order);
            List<OrderDetail> orderDetails = iOderDetailRepo.getAllOrdersDetailByOrderId(order.getId());
            for (OrderDetail od :orderDetails) {
                Product product = iProductRepo.findById(od.getProduct().getId());
                product.setQuantity(product.getQuantity() + od.getQuantity());
                iProductRepo.save(product);
            }
        }else {
            iOderRepo.save(order);
        }
        return null;
    }

    @Override
    public Order save(Order order) {
        return iOderRepo.save(order);
    }
    @Override
    public List<Order> getByStatusAndCustomer(long statusId, long customerId) {
        return iOderRepo.getAllByStatusIdAndUserId(statusId, customerId);
    }

    @Override
    public List<Order> getAllOrdersByCustomerId(long idCustomer) {
        return iOderRepo.getAllOrdersByCustomerId(idCustomer);
    }

    @Override
    public List<Order> getAllOrdersByShopId(long idShop) {
        return iOderRepo.getAllOrdersByShopId(idShop);
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
