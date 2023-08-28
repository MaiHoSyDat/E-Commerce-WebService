package com.example.case6.repository;

import com.example.case6.model.Member;
import com.example.case6.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationRepo extends JpaRepository<Notification, Long> {
}
