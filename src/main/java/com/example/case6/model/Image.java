package com.example.case6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private String image;
    @ManyToOne
    private Product product;

}
