package com.example.case6.service;

import com.example.case6.model.Employee;
import com.example.case6.model.Shop;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> findAll();
    List<Object[]> getAllEmployee();
    Employee updateEmployee(Employee employee);
    Employee findEmployeeByAccountId(long id);
    void save (Employee employee);

    Optional<Employee> findById(Long id);

    Employee getEmployeeByAccountLogin(long account_id);

}
