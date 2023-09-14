package com.example.case6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeDTO {
    private long id;
    private String name;
    private int quantity;
    private double percent;
    private long shopId;
}
