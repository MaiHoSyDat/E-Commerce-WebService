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
    private Status status = new Status(1);
    @ManyToOne
    private Customer user;
    private double totalAmount;

    public Order(Customer user, double totalAmount) {
        this.user = user;
        this.totalAmount = totalAmount;
    }
}
