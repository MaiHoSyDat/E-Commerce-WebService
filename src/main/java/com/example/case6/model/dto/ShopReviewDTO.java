package com.example.case6.model.dto;

import com.example.case6.model.Shop;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShopReviewDTO {
    private Shop shop;
    private Double average_rating;
    private Long total_reviews;
    public ShopReviewDTO(Shop shop, Double average_rating, Long total_reviews) {
        this.shop = shop;
        this.average_rating = average_rating;
        this.total_reviews = total_reviews;
    }

}
