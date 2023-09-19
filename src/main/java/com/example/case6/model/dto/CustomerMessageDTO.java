package com.example.case6.model.dto;

import com.example.case6.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerMessageDTO {
    private Customer customer;
    private Long idMessage;

    public CustomerMessageDTO(Customer customer, Long idMessage) {
        this.customer = customer;
        this.idMessage = idMessage;
    }
}
