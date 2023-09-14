package com.example.case6.repository;

import com.example.case6.model.Code;
import com.example.case6.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICodeRepo extends JpaRepository<Code, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM Code where shop_id= :shop_id")
    List<Code> getAllCodeByShopId(@Param("shop_id") long shop_id);
}
