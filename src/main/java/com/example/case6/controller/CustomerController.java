package com.example.case6.controller;

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
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @GetMapping("/buyProductOfShop/{idShop}")
    public ResponseEntity<List<Customer>> getAllCustomerBuyProductFromShop(@PathVariable long idShop) {
        return new ResponseEntity<>(customerService.getAllCustomerBuyProductFromShop(idShop), HttpStatus.OK);
    }
}
