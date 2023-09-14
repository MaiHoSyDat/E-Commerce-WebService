package com.example.case6.model.dto;

import com.example.case6.model.Customer;
import com.example.case6.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private long id;
    private String headline;
    private String context ;
    private CustomerDTO customer;
    private Date date ;
    private int rating;
    private String accountName;

}
