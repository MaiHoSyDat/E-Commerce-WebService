package com.example.case6.repository;

import com.example.case6.model.Cart;
import com.example.case6.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepo extends JpaRepository<Category, Long> {
}
