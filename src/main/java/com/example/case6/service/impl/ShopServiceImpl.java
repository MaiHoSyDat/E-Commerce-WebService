package com.example.case6.service.impl;

import com.example.case6.model.CartDetail;
import com.example.case6.model.OrderDetail;
import com.example.case6.model.Shop;
import com.example.case6.repository.IShopRepo;
import com.example.case6.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private IShopRepo iShopRepo;

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
    public void saveShop(Shop shop) {
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
    public List<Shop> getAllShopByProductInCartDetail(List<CartDetail> cartDetails) {
        List<Shop> shops = new ArrayList<>();
        for (CartDetail cd : cartDetails) {
            boolean isShopExist = false;
            for (Shop s : shops) {
                if (s.getId() == cd.getProduct().getShop().getId()) {
                    isShopExist = true;
                    break;
                }
            }
            if (!isShopExist) {
                shops.add(cd.getProduct().getShop());
            }
        }
        return shops;
    }

    @Override
    public List<Shop> getAllShopByProductInOrderDetail(List<OrderDetail> orderDetails) {
        List<Shop> shops = new ArrayList<>();
        for (OrderDetail od : orderDetails) {
            boolean isShopExist = false;
            for (Shop s : shops) {
                if (s.getId() == od.getProduct().getShop().getId()) {
                    isShopExist = true;
                    break;
                }
            }
            if (!isShopExist) {
                shops.add(od.getProduct().getShop());
            }
        }
        return shops;
    }

}
