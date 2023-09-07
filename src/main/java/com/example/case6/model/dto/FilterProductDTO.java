package com.example.case6.model.dto;

import com.example.case6.model.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterProductDTO {
    private String nameProduct;
    private String category;
    private List<Long> idShops;
    private Double minPrice;
    private Double maxPrice;
    private List<Integer> ratings;
    private String sort;
    private String quantity;
}
