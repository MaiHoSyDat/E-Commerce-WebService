package com.example.case6.controller;

import com.example.case6.model.Account;
import com.example.case6.model.Customer;
import com.example.case6.model.dto.EditPassDTO;
import com.example.case6.service.IAccountService;
import com.example.case6.service.ICustomerService;
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
    public ResponseEntity<Account> changePassword(@RequestParam String username, @RequestParam String password){
        return new ResponseEntity<>(iAccountService.changePassword(username,password), HttpStatus.OK);
    }
    @PostMapping("/editPass")
    public ResponseEntity<?> editPass(@RequestBody EditPassDTO editPassDTO){
        if ( iAccountService.editPass(editPassDTO)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
