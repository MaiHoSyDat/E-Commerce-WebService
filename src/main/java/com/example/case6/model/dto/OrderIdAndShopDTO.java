package com.example.case6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderIdAndShopDTO {
    private long orderId;
    private long shopId;
    private String shopName;
}
