package com.example.case6.repository;

import com.example.case6.model.Product;
import com.example.case6.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReviewRepo extends JpaRepository<Review, Long> {
    @Query(nativeQuery = true, value = "SELECT p.product_id, p.product_name, AVG(r.star) AS average_star" +
            "FROM product p" +
            "JOIN review r ON p.product_id = r.product_id" +
            "GROUP BY p.product_id, p.product_name;")
    List<Product> getReviewByStar();
}
