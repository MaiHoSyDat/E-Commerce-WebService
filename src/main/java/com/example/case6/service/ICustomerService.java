package com.example.case6.service;

import com.example.case6.model.Account;
import com.example.case6.model.Customer;

public interface ICustomerService {
    Customer getByAccount(Account account);

}
