package com.example.case6.service.impl;

import com.example.case6.model.*;
import com.example.case6.model.dto.CustomerDTO;
import com.example.case6.repository.IAccountRepo;
import com.example.case6.repository.ICustomerRepo;
import com.example.case6.repository.IWishlistRepo;
import com.example.case6.service.IAccountService;
import com.example.case6.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    ICustomerRepo iCustomerRepo;
    @Autowired
    IAccountRepo iAccountRepo;
    @Autowired
    IWishlistRepo iWishlistRepo;

    @Override
    public Customer save(Customer customer) {
        Account account = iAccountRepo.findById(customer.getAccount().getId());
        account.setStatus(new Status(1));
        iAccountRepo.save(account);
        Customer customer1 = iCustomerRepo.save(customer);
        Wishlist wishlist = new Wishlist();
        wishlist.setAccount(customer1);
        wishlist.setProducts(new ArrayList<>());
        iWishlistRepo.save(wishlist);
        return customer1;
    }

    @Override
    public List<Customer> getAll() {
        return iCustomerRepo.findAll();
    }

    @Override
    public Customer getByAccount(Account account) {
        return iCustomerRepo.findByAccountId(account.getId());
    }

    @Override
    public Customer getCustomerByAccountLogin(long idAccount) {
        return iCustomerRepo.getCustomerByAccountLogin(idAccount);
    }

    @Override
    public CustomerDTO findById(long id) {
        Account account = iAccountRepo.findById(id);
        Customer customer = iCustomerRepo.getCustomerByAccount_Id(account.getId());
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(),customer.getBirthday()
                ,customer.getDate_create(), customer.getAvatar(), customer.getAddress(), customer.getPhone(), customer.getGender());
        return customerDTO;
    }

    @Override
    public CustomerDTO edit(CustomerDTO customerDTO) {
        Customer customer = iCustomerRepo.findById(customerDTO.getId());
        customer.setAddress(customerDTO.getAddress());
        customer.setAvatar(customerDTO.getAvatar());
        customer.setGender(customerDTO.getGender());
        customer.setBirthday(customerDTO.getBirthday());
        customer.setPhone(customerDTO.getPhone());
        iCustomerRepo.save(customer);
        System.out.println(customer);
        return customerDTO;
    }

    @Override
    public List<Customer> getAllCustomerBuyProductFromShop(long idShop) {
        String hql = "SELECT c " +
                " FROM Customer c " +
                " JOIN Order o ON o.user.id = c.id " +
                " JOIN Shop s ON s.id = o.shop.id " +
                " WHERE s.id = :idShop " +
                " GROUP BY  c.id ";
        List<Customer> result = entityManager.createQuery(hql, Customer.class)
                .setParameter("idShop", idShop)
                .getResultList();
        return result;
    }
}
