package com.example.case6.service.impl;

import com.example.case6.model.Account;
import com.example.case6.repository.IAccountRepo;
import com.example.case6.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        System.out.println(account);
        return iAccountRepo.save(account);
    }

    @Override
    public Account edit(Account account) {
        return iAccountRepo.save(account);
    }

    @Override
    public void deleteById(long id) {
        iAccountRepo.deleteById(id);
    }

    @Override
    public Page<Account> getAllByRoleId(Pageable pageable, long id) {
        return iAccountRepo.findAllByRoleId(pageable, id);
    }

    @Override
    public Page<Account> getAllByLike(Pageable pageable, int num, String context) {
        if (num == 1) {
            return iAccountRepo.findAllByFull_nameLike("%" + context + "%", pageable);
        } else if (num == 2) {
            return iAccountRepo.findAllByEmailLike("%" + context + "%" , pageable);
        }
        return null;
    }

}
