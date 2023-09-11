package com.example.case6.service;

import com.example.case6.model.Product;
import com.example.case6.model.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProduct();

    Page<Product> getAllProduct(Pageable pageable);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void save(ProductDTO productDTO, long idShop);

    void save(Product product);

    void delete(Long id);

    List<Product> getProductByShopId(long id);

    Product findProductByShopId(long idShop, long idProduct);

    void edit(Product product);

    Page<Product> filter(Double minPrice, Double maxPrice, String name, String shopName,Double rating, Pageable pageable);

    List<Product> getAll();

    List<Product> getTenNewProducts();

    Product getById(long id);

}
