package com.example.case6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Code {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int quantity;
    private double percent;
    @ManyToOne
    private Shop shop;
}
