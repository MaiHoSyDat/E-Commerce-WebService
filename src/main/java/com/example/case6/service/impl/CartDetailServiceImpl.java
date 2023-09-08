package com.example.case6.service.impl;

import com.example.case6.model.Cart;
import com.example.case6.model.CartDetail;
import com.example.case6.repository.ICartDetailRepo;
import com.example.case6.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartDetailServiceImpl implements ICartDetailService {
    @Autowired
    ICartDetailRepo iCartDetailRepo;

    @Override
    public List<CartDetail> getByCart(Cart cart) {
        return  iCartDetailRepo.findAllByCart(cart);
    }

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return iCartDetailRepo.save(cartDetail);
    }

    @Override
    public void deleteCartDetail(long cartDetailId) {
        iCartDetailRepo.deleteById(cartDetailId);
    }
    @Override
    public List<CartDetail> updateQuantityByCart(List<CartDetail> cartDetails){
        List<CartDetail> cartDetails1 = new ArrayList<>();
        for (CartDetail cd: cartDetails) {
            iCartDetailRepo.updateQuantityByCart(cd.getQuantity(),cd.getId());
            cartDetails1.add(iCartDetailRepo.findById(cd.getId()));
        }
        return cartDetails1;
    }
}