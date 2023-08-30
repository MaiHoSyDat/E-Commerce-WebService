package com.example.case6.service;

import com.example.case6.model.Category;
import com.example.case6.model.Product;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    void save(Category category);
    void delete(Long id);
}
