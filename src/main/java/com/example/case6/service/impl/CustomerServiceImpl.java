package com.example.case6.service.impl;

import com.example.case6.model.Account;
import com.example.case6.model.Customer;
import com.example.case6.repository.ICustomerRepo;
import com.example.case6.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    ICustomerRepo iCustomerRepo;
    @Override
    public Customer getByAccount(Account account) {
        return iCustomerRepo.findByAccountId(account.getId());
    }
}
