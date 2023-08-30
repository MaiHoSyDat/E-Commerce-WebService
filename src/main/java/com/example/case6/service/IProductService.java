package com.example.case6.service;

import com.example.case6.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService extends IGeneratedService<Product, Long> {
    Page<Product> getAllProduct(Pageable pageable);


}
