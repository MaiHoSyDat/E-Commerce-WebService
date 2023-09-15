package com.example.case6.repository;

import com.example.case6.model.Account;
import com.example.case6.model.Employee;
import com.example.case6.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findAll();
    @Query("SELECT a.name, a.email, e FROM Account a JOIN a.role r JOIN Employee e ON e.account = a WHERE r.id = 4")
    List<Object[]> getAllEmployee();
    @Query("select e from Employee e where e.account.id = :idAccount")
    Employee getAllByAccountId(@Param("idAccount") long id);
}
