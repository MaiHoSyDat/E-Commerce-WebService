package com.example.case6.repository;

import com.example.case6.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOderDetailRepo extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByOrderId(long orderId);
}
