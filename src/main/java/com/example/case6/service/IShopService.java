package com.example.case6.service;

import com.example.case6.model.Product;
import com.example.case6.model.Shop;
import com.example.case6.model.dto.ShopReviewDTO;

import java.util.List;
import java.util.Optional;

public interface IShopService {

    List<Shop> getAllShop();

    Shop findShopById(long id);

    void save(Shop shop);

    void editShop(Shop shop);

    Shop getShopByAccountLogin(long account_id);

    ShopReviewDTO findShopDTO(long id);

    ShopReviewDTO findShopDTOByAccountLogin(long id);

    List<Shop> getFiveShopsPage(int offset);

    Optional<Shop> findById(Long id);

}
