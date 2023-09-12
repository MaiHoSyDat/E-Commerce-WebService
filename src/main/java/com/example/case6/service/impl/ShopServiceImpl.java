package com.example.case6.service.impl;

import com.example.case6.model.Account;
import com.example.case6.model.Shop;
import com.example.case6.model.Status;
import com.example.case6.repository.IAccountRepo;
import com.example.case6.repository.IShopRepo;
import com.example.case6.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private IShopRepo iShopRepo;
    @Autowired
    private IAccountRepo iAccountRepo;

    @Override
    public List<Shop> getAllShop() {
        return iShopRepo.findAll();
    }

    @Override
    public Shop findShopById(long id) {
        Optional<Shop> shopOptional = iShopRepo.findById(id);
        return shopOptional.orElse(null);
    }

    @Override
    public void save (Shop shop) {
        iShopRepo.save(shop);
    }

    @Override
    public void editShop(Shop shop) {
        iShopRepo.save(shop);
    }

    @Override
    public Shop getShopByAccountLogin(long account_id) {
        return iShopRepo.getShopByAccountLogin(account_id);
    }

    @Override
    public Shop saveShop(Shop shop) {
        return iShopRepo.save(shop);
    }

}
