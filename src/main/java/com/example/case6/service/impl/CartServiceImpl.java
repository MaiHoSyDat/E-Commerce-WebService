package com.example.case6.service.impl;

import com.example.case6.model.Account;
import com.example.case6.model.Cart;
import com.example.case6.model.CartDetail;
import com.example.case6.model.Product;
import com.example.case6.repository.ICartDetailRepo;
import com.example.case6.repository.ICartRepo;
import com.example.case6.service.ICartDetailService;
import com.example.case6.service.ICartService;
import com.example.case6.service.IProductService;
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
    public Cart addToCart(Account account, long productId, int quantity) {
        Cart cart = getByAccount(account);
        Product product = iProductService.getById(productId);
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
                } else {
                    iCartDetailService.save(new CartDetail(0, product, cart, quantity));
                }
                iCartDetailService.save(cartDetails.get(index));
            } else {
                iCartDetailService.save(new CartDetail(0, product, cart, quantity));
            }
            return cart;
        } else {
            cart = new Cart(0, account);
            create(cart);
            CartDetail cartDetail = new CartDetail(0, product, cart, quantity);
            iCartDetailService.save(cartDetail);
        }
        return cart;
    }

    @Override
    public Cart getByAccount(Account account) {
        return iCartRepo.findByAccountId(account.getId());
    }

    @Override
    public void updateCart(List<CartDetail> cartDetails) {
        iCartDetailService.updateQuantityByCart(cartDetails);
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


}