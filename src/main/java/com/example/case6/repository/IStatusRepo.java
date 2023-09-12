package com.example.case6.repository;

import com.example.case6.model.Shop;
import com.example.case6.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStatusRepo extends JpaRepository<Status, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM status WHERE name = 'Pending' OR name = 'Confirm' OR name = 'Received' OR name = 'Cancel'")
    List<Status> getAllStatusOrder();
}
