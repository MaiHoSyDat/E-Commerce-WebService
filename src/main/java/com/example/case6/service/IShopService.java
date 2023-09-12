package com.example.case6.service;

import com.example.case6.model.Customer;
import com.example.case6.model.Shop;

import java.util.List;
import java.util.Optional;

public interface IShopService {

    List<Shop> getAllShop();

    Shop findShopById(long id);

    void save(Shop shop);

    void editShop(Shop shop);

    Shop getShopByAccountLogin(long account_id);

    Shop saveShop (Shop shop);


}
