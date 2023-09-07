package com.example.case6.repository;

import com.example.case6.model.Product;
import com.example.case6.model.Role;
import com.example.case6.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IShopRepo extends JpaRepository<Shop, Long> {
    // tìm shop theo account đăng nhập
    @Query(nativeQuery = true, value = "SELECT * FROM Shop where account_id= :account_id")
    Shop getShopByAccountLogin(@Param("account_id") long account_id);
}
