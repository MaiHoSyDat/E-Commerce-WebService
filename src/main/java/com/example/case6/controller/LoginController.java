package com.example.case6.controller;

import com.example.case6.model.Account;
import com.example.case6.model.dto.AccountToken;
import com.example.case6.service.IAccountService;
import com.example.case6.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    IAccountService accountService;


    @PostMapping
    public AccountToken getLogin(@RequestBody Account account){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        account = accountService.getAccountLogin(account.getUsername(), account.getPassword());
        String token;
        token = jwtService.createToken(authentication);
        return new AccountToken(account.getId(),account.getEmail(),account.getBirthday(),account.getDate_create(),account.getAvatar(),
                account.getAddress(),account.getFull_name(),account.getPhone(),account.getGender(),
                account.getStatus(),account.getSalary(),account.getRole(), token);
    }
}
