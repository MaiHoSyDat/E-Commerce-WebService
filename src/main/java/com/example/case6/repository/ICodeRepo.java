package com.example.case6.repository;

import com.example.case6.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICodeRepo extends JpaRepository<Code, Long> {
    List<Code> findAllByShopId(long shopId);
    Code findById(long id);
    @Query(nativeQuery = true, value = "select * FROM code c WHERE c.shop_id = :shopId AND c.quantity > 0")
    List<Code> findAllByShop(@Param("shopId") long shopId);
}
