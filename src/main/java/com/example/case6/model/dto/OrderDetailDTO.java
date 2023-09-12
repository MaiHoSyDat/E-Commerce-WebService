package com.example.case6.model.dto;

import com.example.case6.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private long id;
    private long orderId;
    private int quantity;
    private String productName;
    private double productPrice;
    private String thumbnail;
}
