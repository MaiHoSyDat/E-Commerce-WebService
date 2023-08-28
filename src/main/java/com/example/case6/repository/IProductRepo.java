package com.example.case6.repository;

import com.example.case6.model.Order;
import com.example.case6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepo extends JpaRepository<Product, Long> {
}
