package com.example.case6.service;

import com.example.case6.model.Account;
import com.example.case6.model.CartDetail;
import com.example.case6.model.OrderDetail;
import com.example.case6.model.Shop;
import com.example.case6.model.dto.ShopCodeDTO;
import com.example.case6.model.dto.ShopReviewDTO;

import java.util.List;

public interface IShopService {
    List<Shop> getAllShop();
    Shop findShopById(long id);
    void saveShop(Shop shop);
    void editShop(Shop shop);
    Shop getShopByAccountLogin(long account_id);
    List<Shop> getAllShopByProductInCartDetail(List<CartDetail> cartDetails);
    List<Shop> getAllShopByProductInOrderDetail(List<OrderDetail> orderDetails);
    List<ShopCodeDTO> getAllShopCode(Account account);
    ShopReviewDTO findShopDTO(long id);
    ShopReviewDTO findShopDTOByAccountLogin(long id);
    List<Shop> getFiveShopsPage(int offset);
    void editAccountShopStatus(long accountId, int statusId);
    void editShopStatus (long shopId, int statusId);
}
