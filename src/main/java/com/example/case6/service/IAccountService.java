package com.example.case6.service;

import com.example.case6.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IAccountService extends UserDetailsService {
    Account getAccountLogin(String username, String password);
    List<Account> getAll();
    Account changePassword(Account account);
    Account findById(int id);
    Account add(Account account);
    Account edit(Account account);
    void delete(int id);
}
