package com.example.case6.service;

import com.example.case6.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IAccountService extends UserDetailsService {
    Account getAccountLogin(String username, String password);
    Account changePassword(Account account);

    Account add(Account account);

}
