package com.example.case6.repository;

import com.example.case6.model.Product;
import com.example.case6.model.Review;
import com.example.case6.model.dto.ProductReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReviewRepo extends JpaRepository<Review, Long> {
    @Query(nativeQuery = true, value = "SELECT p.id, p.price, p.name, c.name AS category_name, AVG(r.rating) AS average_rating, COUNT(r.id) AS total_reviews" +
            "FROM product p" +
            "JOIN category c ON p.category_id = c.id" +
            "JOIN review r ON p.id = r.product_id" +
            "GROUP BY p.id, p.name;")
    List<ProductReviewDTO> getReviewByStar();
    @Query("SELECT r.rating, COUNT(r) FROM Review r where r.product.id = :product GROUP BY r.rating")
    List<Object[]> getTotalReviewByRating(long product);

    List<Review> getAllByProductId (long idProduct);
    @Query(nativeQuery = true, value = " SELECT * " +
            " FROM Review r " +
            " JOIN Customer c ON r.user_id = c.id " +
            " JOIN Product p ON r.product_id = p.id " +
            " WHERE r.product_id = :product_id AND r.user_id = :user_id;")
    List<Review> getAllByProductIdAndCustomerId(@Param("product_id") long product_id, @Param("user_id") long user_id);

}
