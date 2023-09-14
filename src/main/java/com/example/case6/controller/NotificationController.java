package com.example.case6.controller;

import com.example.case6.model.Notification;
import com.example.case6.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;
    @GetMapping("/customer/{idCustomer}")
    public ResponseEntity<List<Notification>> getAllNotificationsByReceiverCustomer(@PathVariable long idCustomer) {
        return new ResponseEntity<>(notificationService.getAllNotificationsByReceiverCustomer(idCustomer), HttpStatus.OK);
    }
}
