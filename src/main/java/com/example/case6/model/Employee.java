package com.example.case6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private double salaryPerDay =0;
    private String gender;

    private double salary =0 ;
    @OneToOne
    private Account account;

}
