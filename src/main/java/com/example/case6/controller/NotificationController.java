package com.example.case6.controller;

import com.example.case6.model.Notification;
import com.example.case6.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
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
    @GetMapping("/shop/{idShop}")
    public ResponseEntity<List<Notification>> getAllNotificationsByReceiverShop(@PathVariable long idShop) {
        return new ResponseEntity<>(notificationService.getAllNotificationsByReceiverShop(idShop), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Notification> save(@RequestBody Notification notification) {
        Date date =  Date.valueOf(LocalDate.now());
        notification.setDate(date);
        notificationService.save(notification);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }
}
