package com.example.case6.service.impl;

import com.example.case6.model.*;
import com.example.case6.model.dto.CodeDTO;
import com.example.case6.model.dto.RevenueDTO;
import com.example.case6.model.dto.ShopCodeDTO;
import com.example.case6.repository.*;
import com.example.case6.model.Account;
import com.example.case6.model.Shop;
import com.example.case6.model.Status;
import com.example.case6.model.dto.ShopReviewDTO;
import com.example.case6.model.Status;
import com.example.case6.repository.IAccountRepo;
import com.example.case6.service.ICartDetailService;
import com.example.case6.service.ICustomerService;
import com.example.case6.service.IAccountService;
import com.example.case6.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    @Autowired
    private IAccountRepo iAccountRepo;
    @Autowired
    private IOderRepo iOderRepo;
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private IShopRepo shopRepo;

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
    public void save(Shop shop) {
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
    public ShopReviewDTO findShopDTO(long idShop) {
        ShopReviewDTO result = entityManager.createQuery("SELECT new com.example.case6.model.dto.ShopReviewDTO(s, AVG(r.rating), COUNT(r.id)) " +
                        " FROM Shop s " +
                        " JOIN Product p ON p.shop.id = s.id " +
                        " LEFT JOIN Review r ON p.id = r.product.id " +
                        " WHERE s.id = :idShop " +
                        " GROUP BY s.id ", ShopReviewDTO.class)
                .setParameter("idShop", idShop)
                .getSingleResult();
        return result;
    }

    @Override
    public ShopReviewDTO findShopDTOByAccountLogin(long id) {
        ShopReviewDTO result = entityManager.createQuery("SELECT new com.example.case6.model.dto.ShopReviewDTO(s, AVG(r.rating), COUNT(r.id)) " +
                        " FROM Shop s " +
                        " JOIN Product p ON p.shop.id = s.id " +
                        " JOIN Account a ON a.id = s.account.id " +
                        " LEFT JOIN Review r ON p.id = r.product.id " +
                        " WHERE a.id = :idAccount " +
                        " GROUP BY s.id ", ShopReviewDTO.class)
                .setParameter("idAccount", id)
                .getSingleResult();
        return result;
    }

    @Override
    public List<Shop> getFiveShopsPage(int offset) {
        String hql = "FROM Shop";
        List<Shop> result = entityManager.createQuery(hql, Shop.class)
                .setFirstResult(offset)
                .setMaxResults(5)
                .getResultList();
        return result;
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

            shopCodeDTOS.add(new ShopCodeDTO(s.getId(),s.getName(),s.getLogo(),codeDTOS, s.getAccount().getId()));
        }
        return shopCodeDTOS;
    }



    @Override
    public void editShopStatus(long shopId, int statusId) {
        Shop shop = iShopRepo.findById(shopId).get();
        shop.setStatus(new Status(statusId));
        iShopRepo.save(shop);
    }

    @Override
    public void editAccountShopStatus(long accountId, int statusId) {
        Account account = iAccountRepo.findById(accountId);
        account.setStatus(new Status(statusId));
        iAccountRepo.save(account);
    }

    @Override
    public Optional<Shop> findById(Long id) {
        return iShopRepo.findById(id);
    }

    @Override
    public List<RevenueDTO> getRevenue(Account account) {
        Shop shop = iShopRepo.getShopByAccountLogin(account.getId());
        return iOderRepo.getRevenueByMonthAndYear(shop.getId());
    }

}
