package com.example.case6.service.impl;

import com.example.case6.model.Product;
import com.example.case6.repository.IProductRepo;
import com.example.case6.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements IProductService {


    @Autowired
    private IProductRepo iProductRepo;

    @Override
    public List<Product> getAllProduct() {
        return iProductRepo.findAll();
    }

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        return iProductRepo.findAll(pageable);
    }

    @Override
    public List<Product> findAll() {
        return iProductRepo.findAll();
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return iProductRepo.findById(aLong);
    }

    @Override
    public List<Product> getProductByShopId(long id) {
        return iProductRepo.getProductByShopId(id);
    }

    @Override
    public Product findProductByShopId(long idShop, long idProduct) {
        return iProductRepo.findProductByShopId(idShop, idProduct);
    }

    @Override
    public void save(Product product) {
        iProductRepo.save(product);
    }

    @Override
    public void delete(Long aLong) {
        iProductRepo.deleteById(aLong);
    }

    @Override
    public void edit(Product product) {
        iProductRepo.save(product);
    }

    @Override
    public Page<Product> filter(Double minPrice, Double maxPrice, String name, String shopName,Double rating, Pageable pageable) {
        return iProductRepo.filter(minPrice, maxPrice, "%" + name + "%", shopName, rating, pageable);
    }
    //lấy ra tất cả sản phẩm theo user
    @Override
    public List<Product> getAll() {
        return iProductRepo.getAll();
    }

    @Override
    public List<Product> getTenNewProducts() {
        return iProductRepo.getTenNewProducts();
    }

    @Override
    public Product getById(long id) {
        return iProductRepo.findById(id);
    }
}

