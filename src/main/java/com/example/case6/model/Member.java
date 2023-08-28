package com.example.case6.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

}
