package com.example.case6.repository;

import com.example.case6.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAccountRepo extends JpaRepository<Account , Long> {
    Account findById(long id);
    Page<Account> findAllByRoleId(Pageable pageable, long id);
    @Query("select a from Account a WHERE a.full_name like :name")
    Page<Account> findAllByFull_nameLike(@Param("name") String name, Pageable pageable);
    @Query("select a from Account a WHERE a.email like :email")
    Page<Account> findAllByEmailLike(@Param("email") String email, Pageable pageable);
    Account getAccountByUsernameAndPassword (String user , String pass );
    Account getAccountByUsername(String username);

}
