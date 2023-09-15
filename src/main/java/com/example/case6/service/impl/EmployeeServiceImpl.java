package com.example.case6.service.impl;

import com.example.case6.model.Employee;
import com.example.case6.repository.IEmployeeRepo;
import com.example.case6.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    IEmployeeRepo iEmployeeRepo;
    @Override
    public void save(Employee employee) {
        iEmployeeRepo.save(employee);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return iEmployeeRepo.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return iEmployeeRepo.findAll();
    }

    @Override
    public List<Object[]> getAllEmployee() {
        return iEmployeeRepo.getAllEmployee();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return iEmployeeRepo.save(employee);
    }

    @Override
    public Employee findEmployeeByAccountId(long id) {
        return iEmployeeRepo.getAllByAccountId(id);
    }


}
