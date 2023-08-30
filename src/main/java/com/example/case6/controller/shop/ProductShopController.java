package com.example.case6.controller.shop;

import com.example.case6.model.Product;
import com.example.case6.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductShopController {

    @Autowired
    private IProductService iProductService;

    @Value("${upload-path}")
    private String upload;

    @GetMapping
    public ResponseEntity<Page<Product>> findAllProduct(@PageableDefault(size = 10)
                                                        Pageable pageable) {
        return new ResponseEntity<>(iProductService.getAllProduct(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        iProductService.save(product);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
                                           @RequestBody Product product) {
        Optional<Product> productOptional = iProductService.findById(id);
        if (productOptional.isPresent()) {
            product.setId(id);
            iProductService.save(product);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Product does not exist", HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Product>> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = iProductService.findById(id);
        if (productOptional.isPresent()) {
            iProductService.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
