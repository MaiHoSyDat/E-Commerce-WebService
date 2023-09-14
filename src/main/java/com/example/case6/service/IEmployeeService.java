package com.example.case6.service;

import com.example.case6.model.Employee;
import com.example.case6.model.Shop;

import java.util.Optional;

public interface IEmployeeService {
    void save (Employee employee);

    Optional<Employee> findById(Long id);
}
