package com.example.case6.controller.customer;

import com.example.case6.model.Customer;
import com.example.case6.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CrudCustomerController {
    @Autowired
    ICustomerService iCustomerService;

    @PostMapping("/save")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(iCustomerService.save(customer) , HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAll(){
        return new ResponseEntity<>(iCustomerService.getAll() , HttpStatus.OK);

    }

}
