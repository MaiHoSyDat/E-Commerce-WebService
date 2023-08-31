package com.example.case6.service;

import com.example.case6.model.Shop;

import java.util.List;

public interface IShopService {
    List<Shop> getAllShop();
    Shop findShopById(long id);
    void saveShop(Shop shop);
    void editShop(Shop shop);
}
