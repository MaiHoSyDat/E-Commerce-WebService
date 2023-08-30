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
    public Page<Product> getAllProduct(Pageable pageable) {
        return iProductRepo.findAll(pageable);
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return iProductRepo.findById(aLong);
    }

    @Override
    public void save(Product product) {
        iProductRepo.save(product);
    }


    @Override
    public void delete(Long aLong) {
        iProductRepo.deleteById(aLong);
    }


}
