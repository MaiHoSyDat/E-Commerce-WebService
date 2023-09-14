package com.example.case6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Product> products;
    @OneToOne
    private Customer account;
}
