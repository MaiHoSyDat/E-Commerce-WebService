package com.example.case6.repository;

import com.example.case6.model.Notification;
import com.example.case6.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOderRepo extends JpaRepository<Order, Long> {
}
