package com.example.case6.controller;

import com.example.case6.model.Account;
import com.example.case6.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    IAccountService iAccountService;
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        return new ResponseEntity<>(iAccountService.add(account), HttpStatus.OK);
    }
}
