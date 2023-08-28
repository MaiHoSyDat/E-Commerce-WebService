package com.example.case6.repository;

import com.example.case6.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepo extends JpaRepository<Account , Long> {
}
