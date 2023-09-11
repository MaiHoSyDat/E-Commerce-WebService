package com.example.case6.service;

import com.example.case6.model.Category;
import com.example.case6.model.Shop;
import com.example.case6.model.dto.ShopReviewDTO;

import java.util.List;

public interface IShopService {
    List<Shop> getAllShop();
    Shop findShopById(long id);
    void saveShop(Shop shop);
    void editShop(Shop shop);
    Shop getShopByAccountLogin(long account_id);
    ShopReviewDTO findShopDTO(long id);
    ShopReviewDTO findShopDTOByAccountLogin(long id);
    List<Shop> getFiveShopsPage(int offset);
}
