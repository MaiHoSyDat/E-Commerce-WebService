package com.example.case6.service;

import com.example.case6.model.Account;
import com.example.case6.model.Cart;
import com.example.case6.model.CartDetail;
import com.example.case6.model.Product;
import com.example.case6.model.dto.CodeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICartService {
    List<Cart> getAll();

    Cart getById(long id);

    Cart create(Cart cart);

    Cart edit(Cart cart);

    void deleteById(long id);

    CartDetail addToCart(Account account, long productId, int quantity);

    Cart getByAccount(Account account);

    List<CartDetail> updateCart(List<CartDetail> cartDetails);
    void deleteProductByCar(long cartDetailId);
    List<CartDetail> getAllCartDetail(Account account);
    boolean payment(Account account, String fullName, String phone, String address, List<CodeDTO> codeDTOs);

}