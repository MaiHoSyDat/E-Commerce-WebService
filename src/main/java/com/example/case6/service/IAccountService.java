package com.example.case6.service;

import com.example.case6.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IAccountService {
    List<Account> getAll();

    Account getById(long id);

    Account create(Account account);

    Account edit(Account account);

    void deleteById(long id);

    Page<Account> getAllByRoleId(Pageable pageable, long id);

    Page<Account> getAllByLike(Pageable pageable, int num, String context);

}
