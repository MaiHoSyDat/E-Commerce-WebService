package com.example.case6.repository;

import com.example.case6.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerRepo extends JpaRepository<Customer , Long> {
}
