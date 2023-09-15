package com.example.case6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
