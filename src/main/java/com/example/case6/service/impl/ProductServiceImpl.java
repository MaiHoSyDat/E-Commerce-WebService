package com.example.case6.service.impl;

import com.example.case6.model.Image;
import com.example.case6.model.Product;
import com.example.case6.model.Shop;
import com.example.case6.model.Status;
import com.example.case6.model.dto.ProductDTO;
import com.example.case6.repository.IImageRepo;
import com.example.case6.repository.IProductRepo;
import com.example.case6.service.IProductService;
import com.example.case6.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
   private IShopService shopService;
    @Autowired
    private IProductRepo iProductRepo;
    @Autowired
    private IImageRepo iImageRepo;

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
    public void save(ProductDTO productDTO, long idShop) {
        Product product = new Product();
        Shop shop = shopService.findShopById(idShop);
        product.setShop(shop);
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(productDTO.getCategory());
        product.setDescription(productDTO.getDescription());
        product.setUnit(productDTO.getUnit());
        product.setThumbnail(productDTO.getThumbnail());
        product.setStatus(new Status(1));
        iProductRepo.save(product);

        Product product1 = iProductRepo.findProductWithMaxId();
        for (int i = 0; i < productDTO.getImages().size(); i++) {
            Image image = new Image();
            image.setProduct(product1);
            image.setImage(productDTO.getImages().get(i));
           iImageRepo.save(image) ;
        }
    }

    @Override
    public void delete(Long aLong) {
        iProductRepo.deleteById(aLong);
    }

    @Override
    public void edit(Product product) {
        iProductRepo.save(product);
    }

    //bộ lọc theo nhiều tiêu chí(hiện taị chỉ đang tìm kiêếm theo full tiêu chí)
    @Override
    public Page<Product> filter(Double minPrice,
                                Double maxPrice,
                                String name,
                                String shopName,
                                Double rating,
                                Pageable pageable) {
        return iProductRepo.filter(minPrice, maxPrice,
                "%" + name + "%", shopName, rating, pageable);
    }

    //lấy ra tất cả sản phẩm theo user
    @Override
    public List<Product> getAll() {
        return iProductRepo.getAll();
    }

    @Override
    public Product getById(long id) {
        return iProductRepo.findById(id);
    }
}

