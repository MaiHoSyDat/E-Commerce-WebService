package com.example.case6.service;

import com.example.case6.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface IAccountService extends UserDetailsService {
  List<Account> getAll();

  Account getById(long id);

  Account create(Account account);

  Account edit(Account account);

  void deleteById(long id);

  Page<Account> getAllByRoleId(Pageable pageable, long id);

//  Page<Account> getAllByLike(Pageable pageable, int num, String context);
  Account getAccountLogin(String username, String password);
  Account changePassword(String username,String password);

  Account add(Account account);
}