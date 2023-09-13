package com.example.case6.service.impl;

import com.example.case6.model.*;
import com.example.case6.model.dto.CustomerDTO;
import com.example.case6.model.dto.ProductDTO;
import com.example.case6.model.dto.ReviewDTO;
import com.example.case6.model.dto.ShopDTO;
import com.example.case6.repository.IImageRepo;
import com.example.case6.repository.IReviewRepo;
import com.example.case6.service.IReviewSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReviewServiceImpl implements IReviewSevice {
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
            reviewDTOS.add(new ReviewDTO(reviews.get(i).getHeadline(),reviews.get(i).getContext(),
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

}
