package com.example.case6.service;

import com.example.case6.model.Customer;
import com.example.case6.model.Account;
import com.example.case6.model.Customer;
import java.util.List;

public interface ICustomerService {

    Customer save(Customer customer);

    List<Customer> getAll();

    Customer getByAccount(Account account);

}
