package com.example.case6.service;

import com.example.case6.model.Cart;
import com.example.case6.model.CartDetail;

import java.util.List;


public interface ICartDetailService {
    List<CartDetail> getByCart(Cart cart);
    CartDetail save(CartDetail cartDetail);
    void deleteCartDetail(long cartDetailId);
    void updateQuantityByCart(List<CartDetail> cartDetails);
}
