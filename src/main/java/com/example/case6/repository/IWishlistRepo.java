package com.example.case6.repository;

import com.example.case6.model.Status;
import com.example.case6.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWishlistRepo extends JpaRepository<Wishlist, Long> {
}
