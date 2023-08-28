package com.example.case6.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Lob
    private String logo;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Positive(message = "Rating should be a positive number")
    private double rating;

    @NotNull(message = "Status is required")
    @ManyToOne
    private Status status;

    private Date dateCreate = Date.valueOf(LocalDate.now());

}