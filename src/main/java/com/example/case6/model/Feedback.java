package com.example.case6.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
public class Feedback {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String headline;
    @Lob
    private String context ;
    @ManyToOne
    private Shop shop;
    @ManyToOne
    private Review reviewMap;
    private Date date =  Date.valueOf(LocalDate.now());
}
