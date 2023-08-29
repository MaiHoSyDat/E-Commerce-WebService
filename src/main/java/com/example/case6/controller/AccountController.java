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
@RequestMapping("/account")
public class AccountController {
    @Autowired
    IAccountService iAccountService;
    @PostMapping("/forgot")
    public ResponseEntity<Account> changePassword(@RequestBody Account account){
        return new ResponseEntity<>(iAccountService.changePassword(account), HttpStatus.OK);
    }
}
