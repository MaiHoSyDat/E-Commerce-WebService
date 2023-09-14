package com.example.case6.controller;

import com.example.case6.model.Review;
import com.example.case6.service.IReviewSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private IReviewSevice reviewSevice;
    @PostMapping
    public ResponseEntity<Review> create(@RequestBody Review review) {
        Date date =  Date.valueOf(LocalDate.now());
        review.setDate(date);
        reviewSevice.save(review);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
    @GetMapping("/{idProduct}/{idCustomer}")
    public ResponseEntity<List<Review>> getAllByProductIdAndCustomerId(@PathVariable long idProduct, @PathVariable long idCustomer) {
        return new ResponseEntity<>(reviewSevice.getAllByProductIdAndCustomerId(idProduct, idCustomer), HttpStatus.OK);
    }
}
