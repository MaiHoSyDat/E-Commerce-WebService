package com.example.case6.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Wishlist {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Product> products;
    @OneToOne
    private Account account;
}
