package com.example.case6.repository;

import com.example.case6.model.Category;
import com.example.case6.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IImageRepo extends JpaRepository<Image, Long> {
    List<Image> findAllByProductId(long id);

}
