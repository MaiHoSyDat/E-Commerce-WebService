package com.example.case6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private String image;
    @ManyToOne
    private Product product;
}
