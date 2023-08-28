package com.example.case6.repository;

import com.example.case6.model.Shop;
import com.example.case6.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusRepo extends JpaRepository<Status, Long> {
}
