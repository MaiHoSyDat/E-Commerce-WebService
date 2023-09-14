package com.example.case6.service.impl;

import com.example.case6.model.Shop;
import com.example.case6.model.Wishlist;
import com.example.case6.repository.IWishlistRepo;
import com.example.case6.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class WishlistServiceImpl implements IWishlistService {
    @Autowired
    private IWishlistRepo iWishlistRepo;
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Wishlist findWishlistByCustomerId(long idCustomer) {
//        String hql = "FROM Wishlist where Wishlist.account.id = :idCustomer";
//        Wishlist result = entityManager.createQuery(hql, Wishlist.class)
//                .setParameter("idCustomer", idCustomer)
//                .getSingleResult();
//        return result;
        return iWishlistRepo.findWishlistByAccountId(idCustomer);
    }

    @Override
    public void updateWishlist(Wishlist wishlist) {
        iWishlistRepo.save(wishlist);
    }
}
