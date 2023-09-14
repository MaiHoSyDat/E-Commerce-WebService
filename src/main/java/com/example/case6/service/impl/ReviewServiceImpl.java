package com.example.case6.service.impl;

import com.example.case6.model.*;
import com.example.case6.model.dto.*;
import com.example.case6.repository.IImageRepo;
import com.example.case6.repository.IReviewRepo;
import com.example.case6.service.IReviewSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
@Service
public class ReviewServiceImpl implements IReviewSevice {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    IReviewRepo iReviewRepo;
    @Autowired
    IImageRepo iImageRepo;
    @Override
    public List<ReviewDTO> getAllReviewByIdProduct(Long idProduct) {
       List<Review> reviews = iReviewRepo.getAllByProductId(idProduct);
        System.out.println(reviews);
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (int i = 0; i < reviews.size(); i++) {
            Customer customer = reviews.get(i).getUser();
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(),customer.getBirthday(), customer.getDate_create(),
                    customer.getAvatar(), customer.getAddress(), customer.getPhone(), customer.getGender());
            reviewDTOS.add(new ReviewDTO(reviews.get(i).getId(),reviews.get(i).getHeadline(),reviews.get(i).getContext(),
                    customerDTO  , reviews.get(i).getDate() , reviews.get(i).getRating(),
                    reviews.get(i).getUser().getAccount().getName()));
        }
        return reviewDTOS;
    }

    @Override
    public List<Object[]> getTotalReviewRating(long productID) {
        List<Object[]> objects =iReviewRepo.getTotalReviewByRating(productID);
        return objects;
    }

    @Override
    public void save(Review review) {
        iReviewRepo.save(review);
    }

    @Override
    public List<Review> getAllByProductIdAndCustomerId(long idProduct, long idCustomer) {
        List<Review> result = entityManager.createQuery("SELECT r " +
                        " FROM Review r " +
                        " JOIN r.user c " +
                        " JOIN r.product p " +
                        " WHERE p.id = :idProduct AND c.id = :idCustomer ", Review.class)
                .setParameter("idProduct", idProduct)
                .setParameter("idCustomer", idCustomer)
                .getResultList();
        return result;
    }

}
