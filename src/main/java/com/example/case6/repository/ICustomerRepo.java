package com.example.case6.repository;

import com.example.case6.model.Customer;
import com.example.case6.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICustomerRepo extends JpaRepository<Customer, Long> {
    Customer findById(long id);
    Customer findByAccountId(long id);
    @Query(nativeQuery = true, value = "SELECT * FROM Customer where account_id= :account_id")
    Customer getCustomerByAccountLogin(@Param("account_id") long account_id);
}