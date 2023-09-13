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
public class ShopCodeDTO {
    private long id;
    private String shopName;
    private String logo;
    private List<CodeDTO> codes;
}
