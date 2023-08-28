package com.example.case6.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany
    private List<Product> products;
    private Date date_create = Date.valueOf(LocalDate.now());
    @ManyToOne
    private Status status;
    @ManyToOne
    private Account user;
}
