package com.example.case6.model.dto;

import com.example.case6.model.Category;
import com.example.case6.model.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private long id;

    private String name;

    private double quantity;

    private double price;

    private Category category;

    private String description;

    private String unit;


    private String thumbnail;

    private ShopDTO shopDTO;
    private Shop shop;

    private Date create_at =  Date.valueOf(LocalDate.now());

    private List<String> images;

    public ProductDTO(long id, String name, double quantity, double price, Category category, String description, String unit, String thumbnail,  Shop shop, Date create_at, List<String> images) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.description = description;
        this.unit = unit;
        this.thumbnail = thumbnail;
        this.shopDTO = shopDTO;
        this.shop = shop;
        this.create_at = create_at;
        this.images = images;
    }

    public ProductDTO(long id, String name, double quantity, double price, Category category, String description, String unit, String thumbnail, ShopDTO shopDTO, Date create_at, List<String> images) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.description = description;
        this.unit = unit;
        this.thumbnail = thumbnail;
        this.shopDTO = shopDTO;
        this.shop = shop;
        this.create_at = create_at;
        this.images = images;
    }
}
