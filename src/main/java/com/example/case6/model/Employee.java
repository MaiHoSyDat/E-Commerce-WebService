package com.example.case6.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Past(message = "Birthday should be in the past")
    private Date birthday;

    private Date date_create = Date.valueOf(LocalDate.now());

    @Lob
    private String avatar;

    @Lob
    private String address;

    @NotBlank(message = "Full name is required")
    private String full_name;

    @NotBlank(message = "Phone number is required")
    private String phone;

    private String gender;

    @ManyToOne
    private Status status;

    private double salary =0 ;
    @OneToOne
    private Account account;



}
