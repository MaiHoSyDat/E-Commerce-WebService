package com.example.case6.service.impl;

import com.example.case6.model.Product;
import com.example.case6.model.Shop;
import com.example.case6.model.dto.FilterProductDTO;
import com.example.case6.model.dto.ProductReviewDTO;
import com.example.case6.repository.IProductRepo;
import com.example.case6.repository.IShopRepo;
import com.example.case6.service.IProductService;
import com.example.case6.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.plaf.PanelUI;
import java.util.*;
import java.lang.*;


@Service
public class ProductServiceImpl implements IProductService {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private IProductRepo iProductRepo;
    @Autowired
    private IShopService shopService;

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
    public Page<Product> filter(Double minPrice, Double maxPrice, String name, String shopName, Double rating, Pageable pageable) {
        return iProductRepo.filter(minPrice, maxPrice, "%" + name + "%", shopName, rating, pageable);
    }

    //lấy ra tất cả sản phẩm theo user
    @Override
    public List<Product> getAll() {
        return iProductRepo.getAll();
    }

    @Override
    public List<ProductReviewDTO> getAllProductsDTO() {
        return iProductRepo.getAllProductsDTO();
    }

    @Override
    public List<ProductReviewDTO> getTenNewProductsDTO() {
        List<ProductReviewDTO> result = entityManager.createQuery("SELECT new com.example.case6.model.dto.ProductReviewDTO(p, AVG(r.rating), COUNT(r.id)) " +
                        " FROM Product p " +
                        " JOIN Category c ON p.category.id = c.id " +
                        " LEFT JOIN Review r ON p.id = r.product.id " +
                        " GROUP BY p.id, p.name " +
                        " Order By p.id desc", ProductReviewDTO.class)
                .setMaxResults(10)
                .getResultList();
        return result;
    }

    @Override
    public List<ProductReviewDTO> getThreeProductsMaxRatingDTO() {
        List<ProductReviewDTO> result = entityManager.createQuery("SELECT new com.example.case6.model.dto.ProductReviewDTO(p, AVG(r.rating), COUNT(r.id)) " +
                        " FROM Product p " +
                        " JOIN Category c ON p.category.id = c.id " +
                        " JOIN Review r ON p.id = r.product.id " +
                        " GROUP BY p.id, p.name " +
                        "ORDER BY AVG(r.rating) DESC", ProductReviewDTO.class)
                .setMaxResults(3)
                .getResultList();
        return result;
    }

    @Override
    public List<ProductReviewDTO> getFilterProductsDTO(FilterProductDTO filterProductDTO) {
        List<Shop> shops = new ArrayList<>();
        if (filterProductDTO.getIdShops().isEmpty()) {
            shops = shopService.getAllShop();
        } else {
            for (long id : filterProductDTO.getIdShops()) {
                shops.add(shopService.findShopById(id));
            }
        }
        if (filterProductDTO.getCategory().equals("All Products") || filterProductDTO.getCategory().equals("All Categories")) {
            filterProductDTO.setCategory(null);
        }
        if (filterProductDTO.getQuantity().equals("")) filterProductDTO.setQuantity(String.valueOf(1000000));

        if (filterProductDTO.getRatings().isEmpty()) {
            if (!filterProductDTO.getSort().equals("Avg. Rating")) {
                if (filterProductDTO.getSort().equals("Low to High")) {
                    String sql = "SELECT new com.example.case6.model.dto.ProductReviewDTO(p, AVG(r.rating), COUNT(r.id)) " +
                            " FROM Product p" +
                            " JOIN Category c ON p.category.id = c.id " +
                            " LEFT JOIN Review r ON p.id = r.product.id " +
                            " WHERE ((p.shop) IN (:listShop)) " +
                            " AND (:nameProduct is null or (p.name like :nameProduct)) " +
                            " AND (:category is null or (c.name = :category)) " +
                            " AND ((:minPrice is null and :maxPrice is null ) or (p.price between :minPrice and :maxPrice)) " +
                            " GROUP BY p.id, p.name " +
                            " ORDER BY p.price asc ";
                    List<ProductReviewDTO> filter = entityManager.createQuery(sql, ProductReviewDTO.class)
                            .setParameter("listShop", shops)
                            .setParameter("minPrice", filterProductDTO.getMinPrice())
                            .setParameter("maxPrice", filterProductDTO.getMaxPrice())
                            .setParameter("category", filterProductDTO.getCategory())
                            .setParameter("nameProduct", "%" + filterProductDTO.getNameProduct() + "%")
                            .setMaxResults(Integer.parseInt(filterProductDTO.getQuantity()))
                            .getResultList();
                    return filter;
                } else {
                    if (filterProductDTO.getSort().equals("High to Low")) filterProductDTO.setSort("p.price");
                    if (filterProductDTO.getSort().equals("Release Date")) filterProductDTO.setSort("p.create_at");
                    if (filterProductDTO.getSort().equals("")) filterProductDTO.setSort(null);
                    String sql = "SELECT new com.example.case6.model.dto.ProductReviewDTO(p, AVG(r.rating), COUNT(r.id)) " +
                            " FROM Product p" +
                            " JOIN Category c ON p.category.id = c.id " +
                            " LEFT JOIN Review r ON p.id = r.product.id " +
                            " WHERE ((p.shop) IN (:listShop)) " +
                            " AND (:nameProduct is null or (p.name like :nameProduct)) " +
                            " AND (:category is null or (c.name = :category)) " +
                            " AND ((:minPrice is null and :maxPrice is null ) or (p.price between :minPrice and :maxPrice)) " +
                            " GROUP BY p.id, p.name " +
                            " ORDER BY " + filterProductDTO.getSort() + " desc ";
                    List<ProductReviewDTO> filter = entityManager.createQuery(sql, ProductReviewDTO.class)
                            .setParameter("listShop", shops)
                            .setParameter("minPrice", filterProductDTO.getMinPrice())
                            .setParameter("maxPrice", filterProductDTO.getMaxPrice())
                            .setParameter("category", filterProductDTO.getCategory())
                            .setParameter("nameProduct", "%" + filterProductDTO.getNameProduct() + "%")
                            .setMaxResults(Integer.parseInt(filterProductDTO.getQuantity()))
                            .getResultList();
                    return filter;
                }

            } else {
                String sql = "SELECT new com.example.case6.model.dto.ProductReviewDTO(p, AVG(r.rating), COUNT(r.id)) " +
                        " FROM Product p" +
                        " JOIN Category c ON p.category.id = c.id " +
                        " JOIN Review r ON p.id = r.product.id " +
                        " WHERE ((p.shop) IN (:listShop)) " +
                        " AND (:nameProduct is null or (p.name like :nameProduct)) " +
                        " AND (:category is null or (c.name = :category)) " +
                        " AND ((:minPrice is null and :maxPrice is null ) or (p.price between :minPrice and :maxPrice)) " +
                        " GROUP BY p.id, p.name " +
                        " ORDER BY AVG(r.rating) desc ";
                List<ProductReviewDTO> filter = entityManager.createQuery(sql, ProductReviewDTO.class)
                        .setParameter("listShop", shops)
                        .setParameter("minPrice", filterProductDTO.getMinPrice())
                        .setParameter("maxPrice", filterProductDTO.getMaxPrice())
                        .setParameter("category", filterProductDTO.getCategory())
                        .setParameter("nameProduct", "%" + filterProductDTO.getNameProduct() + "%")
                        .setMaxResults(Integer.parseInt(filterProductDTO.getQuantity()))
                        .getResultList();
                return filter;
            }
        } else {
            if (filterProductDTO.getSort().equals("Low to High")) {
                String sql = "SELECT new com.example.case6.model.dto.ProductReviewDTO(p, AVG(r.rating), COUNT(r.id)) " +
                        " FROM Product p" +
                        " JOIN Category c ON p.category.id = c.id " +
                        " LEFT JOIN Review r ON p.id = r.product.id " +
                        " WHERE ((p.shop) IN (:listShop)) " +
                        " AND (:nameProduct is null or (p.name like :nameProduct)) " +
                        " AND (:category is null or (c.name = :category)) " +
                        " AND ((:minPrice is null and :maxPrice is null ) or (p.price between :minPrice and :maxPrice)) " +
                        " GROUP BY p.id, p.name " +
                        " HAVING (FLOOR(AVG(r.rating)) IN (:listRating)) " +
                        " ORDER BY p.price asc ";
                List<ProductReviewDTO> filter = entityManager.createQuery(sql, ProductReviewDTO.class)
                        .setParameter("listShop", shops)
                        .setParameter("listRating", filterProductDTO.getRatings())
                        .setParameter("minPrice", filterProductDTO.getMinPrice())
                        .setParameter("maxPrice", filterProductDTO.getMaxPrice())
                        .setParameter("category", filterProductDTO.getCategory())
                        .setParameter("nameProduct", "%" + filterProductDTO.getNameProduct() + "%")
                        .setMaxResults(Integer.parseInt(filterProductDTO.getQuantity()))
                        .getResultList();
                return filter;
            } else {
                if (filterProductDTO.getSort().equals("Avg. Rating")) {
                    String sql = "SELECT new com.example.case6.model.dto.ProductReviewDTO(p, AVG(r.rating), COUNT(r.id)) " +
                            " FROM Product p" +
                            " JOIN Category c ON p.category.id = c.id " +
                            " JOIN Review r ON p.id = r.product.id " +
                            " WHERE ((p.shop) IN (:listShop)) " +
                            " AND (:nameProduct is null or (p.name like :nameProduct)) " +
                            " AND (:category is null or (c.name = :category)) " +
                            " AND ((:minPrice is null and :maxPrice is null ) or (p.price between :minPrice and :maxPrice)) " +
                            " GROUP BY p.id, p.name " +
                            " HAVING (FLOOR(AVG(r.rating)) IN (:listRating)) " +
                            " ORDER BY AVG(r.rating) desc ";
                    List<ProductReviewDTO> filter = entityManager.createQuery(sql, ProductReviewDTO.class)
                            .setParameter("listShop", shops)
                            .setParameter("listRating", filterProductDTO.getRatings())
                            .setParameter("minPrice", filterProductDTO.getMinPrice())
                            .setParameter("maxPrice", filterProductDTO.getMaxPrice())
                            .setParameter("category", filterProductDTO.getCategory())
                            .setParameter("nameProduct", "%" + filterProductDTO.getNameProduct() + "%")
                            .setMaxResults(Integer.parseInt(filterProductDTO.getQuantity()))
                            .getResultList();
                    return filter;
                } else {
                    if (filterProductDTO.getSort().equals("High to Low")) filterProductDTO.setSort("p.price");
                    if (filterProductDTO.getSort().equals("Release Date")) filterProductDTO.setSort("p.create_at");
                    if (filterProductDTO.getSort().equals("")) filterProductDTO.setSort(null);
                    String sql = "SELECT new com.example.case6.model.dto.ProductReviewDTO(p, AVG(r.rating), COUNT(r.id)) " +
                            " FROM Product p" +
                            " JOIN Category c ON p.category.id = c.id " +
                            " JOIN Review r ON p.id = r.product.id " +
                            " WHERE ((p.shop) IN (:listShop)) " +
                            " AND (:nameProduct is null or (p.name like :nameProduct)) " +
                            " AND (:category is null or (c.name = :category)) " +
                            " AND ((:minPrice is null and :maxPrice is null ) or (p.price between :minPrice and :maxPrice)) " +
                            " GROUP BY p.id, p.name " +
                            " HAVING (FLOOR(AVG(r.rating)) IN (:listRating)) " +
                            " ORDER BY "+ filterProductDTO.getSort() +" desc ";
                    List<ProductReviewDTO> filter = entityManager.createQuery(sql, ProductReviewDTO.class)
                            .setParameter("listShop", shops)
                            .setParameter("listRating", filterProductDTO.getRatings())
                            .setParameter("minPrice", filterProductDTO.getMinPrice())
                            .setParameter("maxPrice", filterProductDTO.getMaxPrice())
                            .setParameter("category", filterProductDTO.getCategory())
                            .setParameter("nameProduct", "%" + filterProductDTO.getNameProduct() + "%")
                            .setMaxResults(Integer.parseInt(filterProductDTO.getQuantity()))
                            .getResultList();
                    return filter;
                }
            }

        }
    }

    @Override
    public Product getById(long id) {
        return iProductRepo.findById(id);
    }
}

