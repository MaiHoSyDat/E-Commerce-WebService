package com.example.case6.model.dto;

import com.example.case6.model.Category;
import com.example.case6.model.Image;
import com.example.case6.model.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private Shop shop;

    private Date create_at =  Date.valueOf(LocalDate.now());

    private List<String> images;
}
