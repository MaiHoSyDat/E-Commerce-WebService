package com.example.case6.repository;

import com.example.case6.model.Product;
import com.example.case6.model.Status;
import com.example.case6.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IWishlistRepo extends JpaRepository<Wishlist, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM Wishlist where account_id = :account_id")
    Wishlist findWishlistByAccountId(@Param("account_id") long account_id);
}
