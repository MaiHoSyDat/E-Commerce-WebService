package com.example.case6.repository;

import com.example.case6.model.Order;
import com.example.case6.model.dto.RevenueDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOderRepo extends JpaRepository<Order, Long> {
    List<Order> getAllByStatusIdAndUserId(long statusId, long userId);
    @Query(nativeQuery = true, value = "SELECT * FROM `Order` where user_id= :user_id order by id desc")
    List<Order> getAllOrdersByCustomerId(@Param("user_id") long user_id);
    @Query(nativeQuery = true, value = "SELECT * FROM `Order` where shop_id= :shop_id order by id desc")
    List<Order> getAllOrdersByShopId(@Param("shop_id") long shop_id);
    @Query(" SELECT new com.example.case6.model.dto.RevenueDTO(MONTH(o.date_create) , YEAR(o.date_create) , SUM(o.totalAmount - c.percent * o.totalAmount)) " +
                   " FROM Order o join Code c ON o.code.id = c.id " +
                   " WHERE o.shop.id = :shopId AND o.status.id = 6 " +
                   " GROUP BY MONTH(o.date_create), YEAR(o.date_create) " +
                   " ORDER BY YEAR(o.date_create), MONTH(o.date_create) ")
    List<RevenueDTO> getRevenueByMonthAndYear(@Param("shopId") long shopId);
}
