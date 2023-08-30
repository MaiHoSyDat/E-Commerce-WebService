package com.example.case6.service;

import com.example.case6.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProductByShopId(long id);
    Product findProductByShopId(long idShop, long idProduct);
    void save(Product product);
    void edit(Product product);
    List<Product> getAll();
}
