package com.example.case6.model.dto;

import com.example.case6.model.Code;
import com.example.case6.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {
    private long id;

    private String name;

    private String logo;

    private String address;

    private String phone;

    private double rating;


    private Status status;

    private Date dateCreate;

    private List<Code> codes;



}
