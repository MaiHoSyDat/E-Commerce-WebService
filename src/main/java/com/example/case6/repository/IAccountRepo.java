package com.example.case6.repository;

import com.example.case6.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountRepo extends JpaRepository<Account , Long> {
    Account findById(long id);
    Account findByUsername(String username);
    //tìm kiếm theo tên
    @Query("select a from Account a WHERE a.name like :name and a.role.id = :roleId")
    Page<Account> findAllByNameLike(@Param("name") String name,@Param("roleId") long roleId, Pageable pageable);
    @Query("select a from Account a WHERE a.email like :email and a.role.id = :roleId")
    Page<Account> findAllByEmailLike(@Param("email") String email,@Param("roleId") long roleId, Pageable pageable);
    Account getAccountByUsernameAndPassword (String user , String pass );
    Account getAccountByUsername(String username);
    Page<Account> findAllByRoleId(Pageable pageable, long id);
    @Query("select a from Account a where a.role.id = 4")
    List<Account> getEmployeeAccount();
    @Query("select a from Account a where a.role.id = 4 and a.username like :username")
    Page<Account> findAllByUsernameLike(@Param("username") String username, Pageable pageable);
}