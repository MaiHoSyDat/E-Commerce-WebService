package com.example.case6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "`Order`")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
        private Date date_create = Date.valueOf(LocalDate.now());
    @ManyToOne
    private Status status = new Status(3);
    @ManyToOne
    private Customer user;
    private double totalAmount;
    private String fullName;
    private String phone;
    private String address;
    @OneToOne
    private Code code;
    @ManyToOne
    private Shop shop;

    public Order(Customer user, Shop shop) {
        this.user = user;
        this.shop = shop;
    }

    public Order(Customer user, String fullName, String phone, String address, Code code, Shop shop) {
        this.user = user;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.code = code;
        this.shop = shop;
    }


    public Order(Customer user, double totalAmount) {
        this.user = user;
        this.totalAmount = totalAmount;
    }
}