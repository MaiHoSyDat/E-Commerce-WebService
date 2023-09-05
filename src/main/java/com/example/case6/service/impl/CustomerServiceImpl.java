package com.example.case6.service.impl;

import com.example.case6.model.Account;
import com.example.case6.model.Customer;
import com.example.case6.model.Status;
import com.example.case6.repository.IAccountRepo;
import com.example.case6.repository.ICustomerRepo;
import com.example.case6.service.IAccountService;
import com.example.case6.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    ICustomerRepo iCustomerRepo;
    @Autowired
    IAccountRepo iAccountRepo;

    @Override
    public Customer save(Customer customer) {
        Account account = iAccountRepo.findById(customer.getAccount().getId());
        account.setStatus(new Status(1));
        iAccountRepo.save(account);
        return iCustomerRepo.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        return iCustomerRepo.findAll();
    }

    @Override
    public Customer getByAccount(Account account) {
        return iCustomerRepo.findByAccountId(account.getId());
    }
}
