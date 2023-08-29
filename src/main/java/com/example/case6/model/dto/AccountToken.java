package com.example.case6.model.dto;
import com.example.case6.model.Account;
import com.example.case6.model.Role;
import com.example.case6.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountToken {
    private long id;//
    private String email;
    private Date birthday;
    private Date date_create;
    private String avatar;
    private String address;
    private String full_name;
    private String phone;
    private String gender;
    private Status status;

    private double salary =0 ;

    private Role role;
    private String token;
}
