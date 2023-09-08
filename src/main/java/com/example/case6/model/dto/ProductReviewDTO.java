package com.example.case6.model.dto;


import com.example.case6.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class ProductReviewDTO {
    private Product product;
    private Double average_rating;
    private Long total_reviews;

    public ProductReviewDTO(Product product, Double average_rating, Long total_reviews) {
        this.product = product;
        this.average_rating = average_rating;
        this.total_reviews = total_reviews;
    }

}
