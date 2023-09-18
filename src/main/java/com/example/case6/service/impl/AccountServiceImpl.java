package com.example.case6.service.impl;

import com.example.case6.model.Account;
import com.example.case6.model.Customer;
import com.example.case6.model.Role;
import com.example.case6.model.Status;
import com.example.case6.model.dto.AccountDTO;
import com.example.case6.model.dto.EditPassDTO;
import com.example.case6.repository.IAccountRepo;
import com.example.case6.repository.ICustomerRepo;
import com.example.case6.repository.IRoleRepo;
import com.example.case6.repository.IStatusRepo;
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
import java.util.Random;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    IAccountRepo iAccountRepo;
    @Autowired
    IStatusRepo iStatusRepo;
    @Autowired
    IRoleRepo iRoleRepo;
    @Autowired
    ICustomerRepo iCustomerRepo;

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
        Role role = iRoleRepo.findById(4);
        Status status = iStatusRepo.findById(1);

        if (role != null) {
            account.setRole(role);
        }

        if (status != null) {
            account.setStatus(status);
        }

        account.setPassword("@123456");

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
        AccountDTO accountDTO = new AccountDTO(account.getId(), account.getEmail(), account.getName(),
                account.getUsername(), account.getStatus(), account.getRole());
        return accountDTO;
    }

    @Override
    public Page<AccountDTO> getAllByLike(Pageable pageable, long roleId, int num, String context) {
        if (num == 1) {
            Page<Account> accountPage = iAccountRepo.findAllByNameLike("%" + context + "%", roleId, pageable);
            Page<AccountDTO> accountDTOS = accountPage.map(this::convertToAccountDTO);
            return accountDTOS;
        } else if (num == 2) {
            Page<Account> accountPage = iAccountRepo.findAllByEmailLike("%" + context + "%", roleId, pageable);
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
    public boolean editPass(EditPassDTO editPassDTO) {
        if (editPassDTO.getNewPass().equals(editPassDTO.getRetypePass())) {
            Account account = iAccountRepo.findById(editPassDTO.getAccountId());
            if (editPassDTO.getPass().equals(account.getPassword())) {
                account.setPassword(editPassDTO.getNewPass());
                iAccountRepo.save(account);
                return true;
            } else
                return false;
        } else
            return false;
    }

    @Override
    public void editStatus(long accountId, int statusId) {
        Account account = iAccountRepo.findById(accountId);
        account.setStatus(new Status(statusId));
        iAccountRepo.save(account);
    }


    @Override
    public Account add(Account account) {
        Account account1 = iAccountRepo.save(account);
        if (account1.getRole() != null)
            if (account1.getRole().getId() == 2) {
                Customer customer = new Customer();
                customer.setAccount(account1);
                customer.setAvatar("https://i.pinimg.com/originals/c6/e5/65/c6e56503cfdd87da299f72dc416023d4.jpg");
                iCustomerRepo.save(customer);
            }

        return account1;
    }

    @Override
    public Account getAccountByUsername(String username) {
        return iAccountRepo.findByUsername(username);
    }

    @Override
    public List<Account> getEmployeeAccount() {
        return iAccountRepo.getEmployeeAccount();
    }


    @Override
    public Optional<Account> findShopByAccountId(Long id) {
        return iAccountRepo.findById(id);
    }

    @Override
    public Account loginGoogle(AccountDTO email) {
        Account account = iAccountRepo.getAccountByUsername(email.getUsername());
        if (account != null) {
            if (account.getRole() == null || account.getRole().getId() == -1) {
                account.setStatus(email.getStatus());
                account.setRole(email.getRole());
            }
            return  add(account);
        } else {
            account = new Account();
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder password = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                int index = random.nextInt(characters.length());
                password.append(characters.charAt(index));
            }
            account.setUsername(email.getUsername());
            account.setPassword(password.toString());
            account.setName(email.getName());
            account.setStatus(email.getStatus());
            account.setEmail(email.getEmail());
            account.setRole(email.getRole());
            add(account);
            return null;
        }
    }

}