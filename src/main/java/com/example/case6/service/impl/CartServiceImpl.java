package com.example.case6.service.impl;

import com.example.case6.model.*;
import com.example.case6.repository.ICartDetailRepo;
import com.example.case6.repository.ICartRepo;
import com.example.case6.repository.ICustomerRepo;
import com.example.case6.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    ICartRepo iCartRepo;
    @Autowired
    ICartDetailService iCartDetailService;
    @Autowired
    IProductService iProductService;
    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    IOrderDetailService iOrderDetailService;
    @Autowired
    IOrderService iOrderService;

    @Override

    public List<Cart> getAll() {
        return iCartRepo.findAll();
    }

    @Override
    public Cart getById(long id) {
        return iCartRepo.findById(id);
    }

    @Override
    public Cart create(Cart cart) {
        return iCartRepo.save(cart);
    }

    @Override
    public Cart edit(Cart cart) {
        return iCartRepo.save(cart);
    }

    @Override
    public void deleteById(long id) {
        iCartRepo.deleteById(id);
    }

    @Override
    public CartDetail addToCart(Account account, long productId, int quantity) {
        Cart cart = getByAccount(account);
        Product product = iProductService.getById(productId);
        CartDetail cartDetail;
        if (cart != null) {

            int index = -1;
            List<CartDetail> cartDetails = iCartDetailService.getByCart(cart);
            if (cartDetails != null) {

                for (int i = 0; i < cartDetails.size(); i++) {
                    if (cartDetails.get(i).getProduct().getId() == productId) {
                        index = i;
                    }
                }

                if (index != -1) {
                    cartDetails.get(index).setQuantity(cartDetails.get(index).getQuantity() + quantity);
                    cartDetail = iCartDetailService.save(cartDetails.get(index));

                } else {
                    cartDetail = iCartDetailService.save(new CartDetail(0, product, cart, quantity));
                }
            } else {
                cartDetail = iCartDetailService.save(new CartDetail(0, product, cart, quantity));
            }
            return cartDetail;
        } else {
            cart = new Cart(0, account);
            create(cart);
            CartDetail cartDetail1 = new CartDetail(0, product, cart, quantity);
            cartDetail = iCartDetailService.save(cartDetail1);
        }
        return cartDetail;
    }

    @Override
    public Cart getByAccount(Account account) {
        return iCartRepo.findByAccountId(account.getId());
    }

    @Override
    public List<CartDetail> updateCart(List<CartDetail> cartDetails) {
        return iCartDetailService.updateQuantityByCart(cartDetails);

    }

    @Override
    public void deleteProductByCar(long cartDetailId) {
        iCartDetailService.deleteCartDetail(cartDetailId);
    }

    @Override
    public List<CartDetail> getAllCartDetail(Account account) {
        Cart cart = getByAccount(account);
        return iCartDetailService.getByCart(cart);
    }

    @Override
    public void payment(Account account, double payment) {
        Cart cart = getByAccount(account);
        List<CartDetail> cartDetails = iCartDetailService.getByCart(cart);
        Customer customer = iCustomerService.getByAccount(account);
        Order order = new Order(customer, payment);
        iOrderService.save(order);
        for (CartDetail cd : cartDetails) {
            OrderDetail orderDetail = new OrderDetail(order, cd.getProduct(), cd.getQuantity());
            iOrderDetailService.save(orderDetail);
            iCartDetailService.deleteCartDetail(cd.getId());
        }
    }


}