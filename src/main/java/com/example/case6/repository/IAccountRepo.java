package com.example.case6.repository;

import com.example.case6.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepo extends JpaRepository<Account, Integer> {
//    @Query(nativeQuery = true, value = "SELECT * FROM Account a WHERE a.`username`= :username and a.password= :password")
//    Account getAccountLogin(@Param("username") String username, @Param("password") String password);
    Account getAccountByUsernameAndPassword (String user , String pass );
    Account getAccountByUsername(String username);
    Account findAccountById(long id);
    Account findAccountByUsername(String username);
}
