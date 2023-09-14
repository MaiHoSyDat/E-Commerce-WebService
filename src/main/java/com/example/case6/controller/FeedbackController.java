package com.example.case6.controller;

import com.example.case6.model.Feedback;
import com.example.case6.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("feedback")
public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;
    @GetMapping("/{idProduct}")
    public ResponseEntity<List<Feedback>> getAllFeedbackByProductId(@PathVariable long idProduct) {
        return new ResponseEntity<>(feedbackService.getAllFeedBackByProductId(idProduct), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Feedback> save(@RequestBody Feedback feedback) {
        Date date =  Date.valueOf(LocalDate.now());
        feedback.setDate(date);
        feedbackService.save(feedback);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }
}
