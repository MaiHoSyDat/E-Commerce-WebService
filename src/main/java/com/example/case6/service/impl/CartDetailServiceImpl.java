package com.example.case6.service.impl;

import com.example.case6.model.Cart;
import com.example.case6.model.CartDetail;
import com.example.case6.repository.ICartDetailRepo;
import com.example.case6.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void updateQuantityByCart(List<CartDetail> cartDetails){
        for (CartDetail cd: cartDetails) {
            iCartDetailRepo.updateQuantityByCart(cd.getQuantity(),cd.getId());
        }
    }
}