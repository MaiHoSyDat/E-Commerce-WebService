package com.example.case6.controller.customer;

import com.example.case6.model.Account;
import com.example.case6.model.Customer;
import com.example.case6.model.Product;
import com.example.case6.service.IAccountService;
import com.example.case6.service.ICustomerService;
import com.example.case6.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CProductController {
    @Autowired
    IProductService iProductService;
    @Autowired
    IAccountService iAccountService;
    @Autowired
    ICustomerService iCustomerService;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(iProductService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/productBuy/{idCustomer}")
    public ResponseEntity<List<Product>> getAllProductsByCustomerBuy(@PathVariable long idCustomer) {
        return new ResponseEntity<>(iProductService.getAllProductsByCustomerBuy(idCustomer), HttpStatus.OK);
    }
    @GetMapping("/getByAccount")
    public ResponseEntity<Customer> getByAccount(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountService.getAccountByUsername(userDetails.getUsername());
        return new ResponseEntity<>(iCustomerService.getByAccount(account),HttpStatus.OK);
    }
}
