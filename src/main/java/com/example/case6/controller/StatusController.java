package com.example.case6.controller;

import com.example.case6.model.Status;
import com.example.case6.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private IStatusService statusService;
    @GetMapping("/order")
    public ResponseEntity<List<Status>> getAllStatusOrder() {
        return new ResponseEntity<>(statusService.getAllStatusOrder(), HttpStatus.OK);
    }
}
