package com.example.case6.service;

import com.example.case6.model.Customer;
import com.example.case6.model.Account;
import com.example.case6.model.Customer;
import com.example.case6.model.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    Customer save(Customer customer);

    List<Customer> getAll();

    Customer getByAccount(Account account);
    Customer getCustomerByAccountLogin(long idAccount);
    CustomerDTO findById(long id);
    CustomerDTO edit(CustomerDTO customerDTO);
    List<Customer> getAllCustomerBuyProductFromShop(long idShop);

}
