package com.example.case6.repository;

import com.example.case6.model.Product;
import com.example.case6.model.dto.ProductReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Query(value = "select p from Product p join Category c " +
            "on p.category.id = c.id " +
            "join Shop s " +
            "on p.shop.id = s.id " +
            "where (:name is null or p.name like :name)" +
            "and (s.name = :shopName or :shopName is null) " +
            "and (p.price between :min and :max or :min is null or :max is null) " +
            "and (:rating is null or s.rating >= :rating)" )
    Page<Product> filter(@Param("min") Double minPrice,
                         @Param("max") Double maxPrice,
                         @Param("name") String name,
                         @Param("shopName") String shopName,
                         @Param("rating") Double rating,
                         Pageable pageable);

    //get 10 new product trang index

    @Query("SELECT new com.example.case6.model.dto.ProductReviewDTO(p, AVG(r.rating), COUNT(r.id))" +
            " FROM Product p " +
            " JOIN Category c ON p.category.id = c.id " +
            " LEFT JOIN Review r ON p.id = r.product.id " +
            " WHERE p.status.id <> 2 " +
            " GROUP BY p.id, p.name " +
            " Order By p.id desc")
    List<ProductReviewDTO> getTenNewProductsDTO();
    //get all product dto
    @Query(nativeQuery = true, value = "SELECT p.id, p.price, p.name, c.name AS category_name, AVG(r.rating) AS average_rating, COUNT(r.id) AS total_reviews " +
            " FROM product p " +
            " JOIN category c ON p.category_id = c.id " +
            " LEFT JOIN review r ON p.id = r.product_id " +
            " GROUP BY p.id, p.name;")
    List<ProductReviewDTO> getAllProductsDTO();
    //get 3 product co rating cao nhat
    @Query(nativeQuery = true, value = "SELECT p.id, p.price, p.name, c.name AS category_name, AVG(r.rating) AS average_rating, COUNT(r.id) AS total_reviews " +
            " FROM product p " +
            " JOIN category c ON p.category_id = c.id " +
            " LEFT JOIN review r ON p.id = r.product_id " +
            " WHERE p.status.id <> 2 " +
            " GROUP BY p.id, p.name " +
            " Order By average_rating desc limit 3;")
    List<ProductReviewDTO> getThreeProductsMaxRatingDTO();

    @Query(nativeQuery = true, value = "SELECT * FROM Product Order By id desc limit 10")
    List<Product> getTenNewProducts();

    @Query("SELECT p FROM Product p WHERE p.id = (SELECT MAX(p2.id) FROM Product p2)")
    Product findProductWithMaxId();
}
