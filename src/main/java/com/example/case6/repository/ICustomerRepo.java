package com.example.case6.repository;

import com.example.case6.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepo extends JpaRepository<Customer, Long> {
    Customer findById(long id);
    Customer findByAccountId(long id);
    Customer getCustomerByAccount_Id(long id);
}