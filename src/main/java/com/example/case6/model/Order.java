package com.example.case6.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "`Order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date_create = Date.valueOf(LocalDate.now());
    @ManyToOne
    private Status status;
    @ManyToOne
    private Customer user;
    private double totalAmount;
}
