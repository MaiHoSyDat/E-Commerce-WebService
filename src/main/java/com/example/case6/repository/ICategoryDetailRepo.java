package com.example.case6.repository;

import com.example.case6.model.Category;
import com.example.case6.model.CategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryDetailRepo extends JpaRepository<CategoryDetail, Long> {
}
