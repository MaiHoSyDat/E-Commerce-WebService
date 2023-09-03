package com.example.case6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;
    private int quantity;

    public OrderDetail(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}
