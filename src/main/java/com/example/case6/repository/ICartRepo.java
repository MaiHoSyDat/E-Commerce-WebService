package com.example.case6.repository;

import com.example.case6.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICartRepo extends JpaRepository<Cart, Long> {
    Cart findById(long id);
    @Query(nativeQuery = true, value = "select * FROM Cart c WHERE c.account_id = :id")
    Cart findByAccountId(@Param("id") long id);
}