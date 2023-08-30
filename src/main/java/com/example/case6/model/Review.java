package com.example.case6.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String headline;
    @Lob
    private String context ;
    @ManyToOne
    private Customer user;
    @ManyToOne
    private Product product;
    private Date date =  Date.valueOf(LocalDate.now());
    private int rating;

}
