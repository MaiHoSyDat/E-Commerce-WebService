package com.example.case6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private long id;

    private Date birthday;

    private Date date_create;


    private String avatar;

    private String address;


    private String phone;

    private String gender;


}
