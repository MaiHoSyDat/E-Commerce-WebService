package com.example.case6.service.impl;

import com.example.case6.model.Account;
import com.example.case6.model.Shop;
import com.example.case6.model.dto.ShopReviewDTO;
import com.example.case6.model.Status;
import com.example.case6.repository.IAccountRepo;
import com.example.case6.repository.IShopRepo;
import com.example.case6.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements IShopService {
    @Autowired
    private IShopRepo iShopRepo;
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private IAccountRepo iAccountRepo;

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
    public Optional<Shop> findById(Long id) {
        return iShopRepo.findById(id);
    }

}
