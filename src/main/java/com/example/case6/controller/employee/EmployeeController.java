package com.example.case6.controller.employee;

import com.example.case6.model.Account;
import com.example.case6.model.Employee;
import com.example.case6.model.Status;
import com.example.case6.repository.IAccountRepo;
import com.example.case6.service.IAccountService;
import com.example.case6.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IAccountRepo iAccountRepo;

    @GetMapping("/login/{idAccount}")
    public ResponseEntity<Employee> getEmployeeByAccountLogin(@PathVariable long idAccount) {
        return new ResponseEntity<>(employeeService.getEmployeeByAccountLogin(idAccount), HttpStatus.OK);
    }

    @PostMapping("/save/{idAccount}")
    public ResponseEntity<?> saveStaff(@PathVariable Long idAccount,
                                      @RequestBody Employee employee) {
        Optional<Account> accountOptional = iAccountService.getAccountByAccountId(idAccount);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            employee.setAccount(account);
            employeeService.save(employee);
            account.setStatus(new Status(1));
            iAccountRepo.save(account);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/save/employee/{idEmployee}")
    public ResponseEntity<?> editInformationStaff(@PathVariable Long idEmployee,
                                                 @RequestBody Employee employee) {
        Optional<Employee> employeeOptional = employeeService.findById(idEmployee);
        if (employeeOptional.isPresent()) {
            employee.setId(idEmployee);
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
