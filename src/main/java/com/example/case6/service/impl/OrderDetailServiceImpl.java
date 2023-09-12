package com.example.case6.service.impl;

import com.example.case6.model.*;
import com.example.case6.model.dto.CodeDTO;
import com.example.case6.model.dto.OrderDTO;
import com.example.case6.model.dto.OrderDetailDTO;
import com.example.case6.model.dto.OrderIdAndShopDTO;
import com.example.case6.repository.ICartRepo;
import com.example.case6.repository.ICodeRepo;
import com.example.case6.repository.IOderDetailRepo;
import com.example.case6.repository.IOderRepo;
import com.example.case6.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    IOderDetailRepo iOderDetailRepo;
    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    ICartDetailService iCartDetailService;
    @Autowired
    ICartRepo iCartRepo;
    @Autowired
    IShopService iShopService;
    @Autowired
    IOrderService iOrderService;
    @Autowired
    ICodeRepo iCodeRepo;

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return iOderDetailRepo.save(orderDetail);
    }

    @Override
    public void createOrderDetail(Account account) {
        Customer customer = iCustomerService.getByAccount(account);
        Cart cart = iCartRepo.findByAccountId(account.getId());
        List<CartDetail> cartDetails = iCartDetailService.getByCart(cart);
        List<Shop> shops = iShopService.getAllShopByProductInCartDetail(cartDetails);
        for (Shop s : shops) {
            Order order = new Order(customer,s);
            iOrderService.save(order);
            for (CartDetail cd : cartDetails) {
                if (cd.getProduct().getShop().getId() == s.getId()) {
                    OrderDetail orderDetail = new OrderDetail(order, cd.getProduct(), cd.getQuantity());
                    save(orderDetail);
                    iCartDetailService.deleteCartDetail(cd.getId());
                }
            }
        }
    }

    @Override
    public List<OrderDTO> getOrderByUnpaid(Account account) {
        Customer customer = iCustomerService.getByAccount(account);
        List<Order> orders = iOrderService.getByStatusAndCustomer(5, customer.getId());
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order o : orders) {
            List<Code> codes = iCodeRepo.findAllByShopId(o.getShop().getId());
            List<OrderDetail> orderDetails = iOderDetailRepo.findAllByOrderId(o.getId());
            List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
            for (OrderDetail od : orderDetails) {
                orderDetailDTOs.add(new OrderDetailDTO(od.getId(), o.getId(), od.getQuantity(), od.getProduct().getName(),
                        od.getProduct().getPrice(), od.getProduct().getThumbnail()));
            }
            List<CodeDTO> codeDTOS = new ArrayList<>();
            for (Code c: codes) {
                codeDTOS.add(new CodeDTO(c.getId(),c.getName(),c.getQuantity(),c.getPercent(),c.getShop().getId()));
            }
            orderDTOS.add(new OrderDTO(o.getId(),o.getShop().getId(),o.getShop().getName(),o.getShop().getLogo(),o.getTotalAmount(),orderDetailDTOs,codeDTOS));
        }
        return orderDTOS;
    }
}
