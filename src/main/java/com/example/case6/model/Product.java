package com.example.case6.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Date;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @DecimalMin(value = "0", message = "Quantity should be greater than or equal to 0")
    private double quantity;

    @DecimalMin(value = "0", message = "Price should be greater than or equal to 0")
    private double price;

    @NotNull(message = "Category is required")
    @ManyToOne
    private Category category;

    @NotBlank(message = "Description is required")
    @Lob
    private String description;

    @NotBlank(message = "Unit is required")
    private String unit;

    @NotBlank(message = "Thumbnail is required")
    @Lob
    private String thumbnail;

    @NotNull(message = "Status is required")
    @ManyToOne
    private Status status;

    @NotNull(message = "Shop is required")
    @ManyToOne
    private Shop shop;

    @NotNull(message = "Creation date is required")
    private Date create_at;

}