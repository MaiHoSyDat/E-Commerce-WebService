package com.example.case6.controller.customer;

import com.example.case6.model.Product;
import com.example.case6.service.IProductService;
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
@RequestMapping("/customer")
public class CProductController {
    @Autowired
    IProductService iProductService;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(iProductService.getAll(), HttpStatus.OK);
    }
}
