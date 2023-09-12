package com.example.case6.service;

import com.example.case6.model.Account;
import com.example.case6.model.dto.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;


public interface IAccountService extends UserDetailsService {
    List<Account> getAll();

    Account getById(long id);

    Account create(Account account);

    Account edit(Account account);

    void deleteById(long id);

    Page<AccountDTO> getAllByRoleId(Pageable pageable, long id);

    AccountDTO convertToAccountDTO(Account account);

    Page<AccountDTO> getAllByLike(Pageable pageable, int num, String context);

    Account getAccountLogin(String username, String password);

    Account changePassword(String username, String password);

    void editStatus(long accountId, int statusId);

    Account add(Account account);

    Account getAccountByUsername(String username);

    Optional<Account> findShopByAccountId(Long id);

}