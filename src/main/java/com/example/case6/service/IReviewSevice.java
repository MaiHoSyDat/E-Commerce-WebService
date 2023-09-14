package com.example.case6.service;

import com.example.case6.model.Review;
import com.example.case6.model.dto.ReviewDTO;

import java.util.List;

public interface IReviewSevice {
    List<ReviewDTO> getAllReviewByIdProduct(Long idProduct);
    List<Object[]> getTotalReviewRating(long productID);
    void save(Review review);
    List<Review> getAllByProductIdAndCustomerId(long idProduct, long idCustomer);
}
