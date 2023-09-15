package com.example.case6.repository;

import com.example.case6.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IOderDetailRepo extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByOrderId(long orderId);
    @Query(nativeQuery = true, value = "SELECT * FROM Order_Detail where order_id= :order_id")
    List<OrderDetail> getAllOrdersDetailByOrderId(@Param("order_id") long order_id);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM order_detail WHERE order_id = :order_id", nativeQuery = true)
    void deleteByOrderId(@Param("order_id") long order_id);
}
