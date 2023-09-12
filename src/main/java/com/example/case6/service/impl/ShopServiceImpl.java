package com.example.case6.service.impl;

import com.example.case6.model.*;
import com.example.case6.model.dto.CodeDTO;
import com.example.case6.model.dto.ShopCodeDTO;
import com.example.case6.repository.ICartRepo;
import com.example.case6.repository.ICodeRepo;
import com.example.case6.repository.IShopRepo;
import com.example.case6.service.ICartDetailService;
import com.example.case6.service.ICustomerService;
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
    @Autowired
    ICodeRepo iCodeRepo;
    @Autowired
    ICustomerService iCustomerService;
    @Autowired
    ICartDetailService iCartDetailService;
    @Autowired
    ICartRepo iCartRepo;

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
    @Override
    public List<ShopCodeDTO> getAllShopCode(Account account) {
        Cart cart = iCartRepo.findByAccountId(account.getId());
        List<CartDetail> cartDetails = iCartDetailService.getByCart(cart);
        List<Shop> shops = getAllShopByProductInCartDetail(cartDetails);
        List<ShopCodeDTO> shopCodeDTOS = new ArrayList<>();
        for (Shop s : shops) {
            List<Code> codes = iCodeRepo.findAllByShop(s.getId());
            List<CodeDTO> codeDTOS = new ArrayList<>();
            for (Code c:codes) {
                codeDTOS.add(new CodeDTO(c.getId(),c.getName(),c.getQuantity(),c.getPercent(),c.getShop().getId()));
            }
            shopCodeDTOS.add(new ShopCodeDTO(s.getId(),s.getName(),s.getLogo(),codeDTOS));
        }
        return shopCodeDTOS;
    }
}
