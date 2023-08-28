package com.example.case6.repository;

import com.example.case6.model.Product;
import com.example.case6.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepo extends JpaRepository<Review, Long> {
}
