package com.example.case6.repository;

import com.example.case6.model.Role;
import com.example.case6.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShopRepo extends JpaRepository<Shop, Long> {
}
