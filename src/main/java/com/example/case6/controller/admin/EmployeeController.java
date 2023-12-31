package com.example.case6.controller.admin;

import com.example.case6.model.Account;
import com.example.case6.model.Employee;
import com.example.case6.model.Shop;
import com.example.case6.model.Status;
import com.example.case6.repository.IAccountRepo;
import com.example.case6.repository.IShopRepo;
import com.example.case6.service.IAccountService;
import com.example.case6.service.IEmployeeService;
import com.example.case6.service.IShopService;
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

    @PostMapping("/save/{idAccount}")
    public ResponseEntity<?> saveShop(@PathVariable Long idAccount,
                                      @RequestBody Employee employee) {
        Optional<Account> accountOptional = iAccountService.findShopByAccountId(idAccount);
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
    public ResponseEntity<?> editInformationShop(@PathVariable Long idEmployee,
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
