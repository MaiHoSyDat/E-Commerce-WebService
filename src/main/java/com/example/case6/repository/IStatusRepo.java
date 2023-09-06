package com.example.case6.repository;

import com.example.case6.model.Shop;
import com.example.case6.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IStatusRepo extends JpaRepository<Status, Long> {
    @Query(value = "select new Status(s.id, s.name) from Status s")
    List<Status> getAllStatus();
}
