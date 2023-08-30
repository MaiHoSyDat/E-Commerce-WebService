package com.example.case6.service.impl;

import com.example.case6.model.Account;
import com.example.case6.model.Status;
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
        return null;
    }


    @Override
    public Account edit(Account account) {
        return iAccountRepo.save(account);
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public Page<Account> getAllByRoleId(Pageable pageable, long id) {
        return null;
    }


//    @Override
//    public Page<Account> getAllByLike(Pageable pageable, int num, String context) {
//        if (num == 1) {
//            return iAccountRepo.findAllByFull_nameLike("%" + context + "%", pageable);
//        } else if (num == 2) {
//            return iAccountRepo.findAllByEmailLike("%" + context + "%" , pageable);
//        }
//        return null;
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepo.getAccountByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add((GrantedAuthority) account.getRole());
        return new User(account.getUsername(),account.getPassword(),roles);
    }

    @Override
    public Account getAccountLogin(String username, String password) {
        return iAccountRepo.getAccountByUsernameAndPassword(username,password);
    }


    @Override
    public Account changePassword(String username,String password) {
        Account account = iAccountRepo.getAccountByUsername(username);
        account.setPassword(password);
        iAccountRepo.save(account);
        return account;
    }


    @Override
    public Account add(Account account) {
        return iAccountRepo.save(account);
    }

}