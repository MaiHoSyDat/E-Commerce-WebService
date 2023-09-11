package com.example.case6.repository;

import com.example.case6.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICodeRepo extends JpaRepository<Code, Long> {
    List<Code> findAllByShopId(long shopId);
}
