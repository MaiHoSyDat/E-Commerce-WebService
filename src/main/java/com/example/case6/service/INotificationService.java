package com.example.case6.service;

import com.example.case6.model.Notification;

import java.util.List;

public interface INotificationService {
    List<Notification> getAllNotificationsByReceiverCustomer(long idCustomer);
}
