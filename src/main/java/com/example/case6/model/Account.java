package com.example.case6.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    @NotBlank(message = "Password is required")
    private String user_name;

    @Size(min = 6, message = "Password should have at least 6 characters")
    @NotBlank(message = "Password is required")
    private String password;

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
    private Member member;

    @ManyToOne
    private Status status;

    private double salary;

    @ManyToOne
    private Role role;
}