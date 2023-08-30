
package com.example.case6.service;

import com.example.case6.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;




public interface IAccountService extends UserDetailsService {
  Account getAccountLogin(String username, String password);
    Account changePassword(String username,String password);

    Account add(Account account);
}