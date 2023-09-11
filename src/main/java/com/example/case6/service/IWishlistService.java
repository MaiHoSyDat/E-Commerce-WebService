package com.example.case6.service;

import com.example.case6.model.Wishlist;

public interface IWishlistService {
    Wishlist findWishlistByCustomerId(long idCustomer);
    void updateWishlist(Wishlist wishlist);
}
