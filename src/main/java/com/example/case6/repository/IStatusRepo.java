package com.example.case6.repository;

import com.example.case6.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.List;

public interface IStatusRepo extends JpaRepository<Status, Long> {
    @Query(value = "select new Status(s.id, s.name) from Status s")
    List<Status> getAllStatus();
    @Query(value = "select new Status(s.id, s.name) from Status s where s.id = 1 or s.id = 2")
    List<Status> getCustomerStatus();
    Status findById(long id);
    @Query(value = "select new Status(s.id, s.name) from Status s where s.id = 1 or s.id = 2 or s.id = 3")
    List<Status> getShopStatus();
    @Query(nativeQuery = true, value = "SELECT * FROM status WHERE name = 'Pending' OR name = 'Confirm' OR name = 'Received' OR name = 'Cancel'")
    List<Status> getAllStatusOrder();
}
