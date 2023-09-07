package com.example.case6.service;

import com.example.case6.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    List<Object[]> getAllEmployee();
}
