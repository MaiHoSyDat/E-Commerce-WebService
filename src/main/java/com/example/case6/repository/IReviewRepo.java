package com.example.case6.repository;

import com.example.case6.model.Product;
import com.example.case6.model.Review;
import com.example.case6.model.dto.ProductReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReviewRepo extends JpaRepository<Review, Long> {
    @Query(nativeQuery = true, value = "SELECT p.id, p.price, p.name, c.name AS category_name, AVG(r.rating) AS average_rating, COUNT(r.id) AS total_reviews" +
            "FROM product p" +
            "JOIN category c ON p.category_id = c.id" +
            "JOIN review r ON p.id = r.product_id" +
            "GROUP BY p.id, p.name;")
    List<ProductReviewDTO> getReviewByStar();
}
