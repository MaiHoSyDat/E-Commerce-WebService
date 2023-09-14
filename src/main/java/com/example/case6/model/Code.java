package com.example.case6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Code {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NotBlank(message = "Name is required")
    private String name;
    @DecimalMin(value = "0", message = "Quantity should be greater than or equal to 0")
    private int quantity;
    @DecimalMin(value = "0", message = "Percent should be greater than or equal to 0")
    private double percent;
    @ManyToOne
    private Shop shop;

    public Code(long id) {
        this.id = id;
    }
}
