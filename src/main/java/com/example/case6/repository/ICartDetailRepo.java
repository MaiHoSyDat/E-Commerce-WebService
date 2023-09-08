package com.example.case6.repository;

import com.example.case6.model.Cart;
import com.example.case6.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Repository
public interface ICartDetailRepo extends JpaRepository<CartDetail, Long> {
    List<CartDetail> findAllByCart(Cart cart);
    CartDetail findById(long id);
    @Modifying
    @Query("UPDATE CartDetail cd SET cd.quantity = :quantity WHERE cd.id = :id")
    void updateQuantityByCart(@Param("quantity") int quantity, @Param("id") long id);
    void deleteById(long id);
}