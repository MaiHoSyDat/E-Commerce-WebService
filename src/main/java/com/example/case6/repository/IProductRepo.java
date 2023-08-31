package com.example.case6.repository;

import com.example.case6.model.Order;
import com.example.case6.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepo extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM Product where shop_id= :shop_id")
    List<Product> getProductByShopId(@Param("shop_id") long shop_id);

    @Query(nativeQuery = true, value = "SELECT * FROM Product where shop_id= :shop_id and id= :id")
    Product findProductByShopId(@Param("shop_id") long shop_id, @Param("id") long id);

    //get all product user
    @Query(nativeQuery = true, value = "SELECT * FROM Product")
    List<Product> getAll();
    Product findById(long id);
}
