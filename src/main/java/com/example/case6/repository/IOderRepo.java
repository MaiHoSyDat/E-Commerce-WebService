package com.example.case6.repository;

import com.example.case6.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOderRepo extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM `Order` where user_id= :user_id")
    List<Order> getAllOrdersByCustomerId(@Param("user_id") long user_id);
    @Query(nativeQuery = true, value = "SELECT * FROM `Order` where shop_id= :shop_id")
    List<Order> getAllOrdersByShopId(@Param("shop_id") long shop_id);
}
