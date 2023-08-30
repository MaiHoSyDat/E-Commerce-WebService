package com.example.case6.service.impl;

import com.example.case6.model.Product;
import com.example.case6.repository.IProductRepo;
import com.example.case6.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepo iProductRepo;

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
    public void edit(Product product) {
        iProductRepo.save(product);
    }
    //lấy ra tất cả sản phẩm theo user và lọc sản phẩm bị ẩn
    @Override
    public List<Product> getAll() {
        return iProductRepo.getAll();
    }

    @Override
    public List<Product> searchProductByName(String name) {
        return iProductRepo.getProductsByName("%" + name +"%");
    }
}
