package com.example.case6.repository;

import com.example.case6.model.Account;
import com.example.case6.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepo extends JpaRepository<Cart, Long> {
    Cart findById(long id);
    Cart findByAccountId(long id);
}
