package com.example.case6.service.impl;

import com.example.case6.model.Product;
import com.example.case6.repository.IProductRepo;
import com.example.case6.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductRepo iProductRepo;
    @Override
    public Product getById(long id) {
        return iProductRepo.findById(id);
    }
}
