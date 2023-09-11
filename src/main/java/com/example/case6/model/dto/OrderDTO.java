package com.example.case6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private long id;
    private long shopId;
    private String shopName;
    private double total_amount;
    private List<OrderDetailDTO> orderDetails;
    private List<CodeDTO> codes;
}
