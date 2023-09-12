package com.example.case6.service.impl;

import com.example.case6.model.Account;
import com.example.case6.model.Role;
import com.example.case6.model.Status;
import com.example.case6.model.dto.AccountDTO;
import com.example.case6.repository.IAccountRepo;
import com.example.case6.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    IAccountRepo iAccountRepo;

    @Override
    public List<Account> getAll() {
        return iAccountRepo.findAll();
    }

    @Override
    public Account getById(long id) {
        return iAccountRepo.findById(id);
    }

    @Override
    public Account create(Account account) {
        account.setRole(new Role(4));
        account.setPassword("@123456");
        account.setStatus(new Status(1));
        return iAccountRepo.save(account);
    }


    @Override
    public Account edit(Account account) {
        return iAccountRepo.save(account);
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Page<AccountDTO> getAllByRoleId(Pageable pageable, long id) {
        Page<Account> accountPage = iAccountRepo.findAllByRoleId(pageable, id);
        Page<AccountDTO> accountDTOS = accountPage.map(this::convertToAccountDTO);
        return accountDTOS;
    }

    @Override
    public AccountDTO convertToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO(account.getId(), account.getEmail(),account.getName(),
                account.getUsername(),account.getStatus(),account.getRole());
        return accountDTO;
    }

    @Override
    public Page<AccountDTO> getAllByLike(Pageable pageable, int num, String context) {
        if (num == 1) {
            Page<Account> accountPage = iAccountRepo.findAllByNameLike("%" + context + "%", pageable);
            Page<AccountDTO> accountDTOS = accountPage.map(this::convertToAccountDTO);
            return accountDTOS;
        } else if (num == 2) {
            Page<Account> accountPage = iAccountRepo.findAllByEmailLike("%" + context + "%", pageable);
            Page<AccountDTO> accountDTOS = accountPage.map(this::convertToAccountDTO);
            return accountDTOS;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepo.getAccountByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add((GrantedAuthority) account.getRole());
        return new User(account.getUsername(), account.getPassword(), roles);
    }

    @Override
    public Account getAccountLogin(String username, String password) {
        return iAccountRepo.getAccountByUsernameAndPassword(username, password);
    }


    @Override
    public Account changePassword(String username, String password) {
        Account account = iAccountRepo.getAccountByUsername(username);
        account.setPassword(password);
        return account;
    }

    @Override
    public void editStatus(long accountId, int statusId) {
        Account account = iAccountRepo.findById(accountId);
        account.setStatus(new Status(statusId));
        iAccountRepo.save(account);
    }


    @Override
    public Account add(Account account) {
        return iAccountRepo.save(account);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return iAccountRepo.findByUsername(username);
    }

    @Override
    public Optional<Account> findShopByAccountId(Long id) {
        return iAccountRepo.findById(id);
    }

}