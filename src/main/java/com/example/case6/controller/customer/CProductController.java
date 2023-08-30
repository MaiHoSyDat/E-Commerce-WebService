package com.example.case6.controller.customer;

import com.example.case6.model.Product;
import com.example.case6.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/products/{name}")
    public ResponseEntity<List<Product>> searchProductByName(@PathVariable String name){
        return new ResponseEntity<>(iProductService.searchProductByName(name),HttpStatus.OK);
    }
}
