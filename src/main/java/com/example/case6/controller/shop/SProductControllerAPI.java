package com.example.case6.controller.shop;

import com.example.case6.model.Product;
import com.example.case6.model.Shop;
import com.example.case6.service.IProductService;
import com.example.case6.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/shops")
public class SProductControllerAPI {
    @Autowired
    private IProductService productService;
    @Autowired
    private IShopService shopService;

    @GetMapping("/{idShop}/products")
    public ResponseEntity<List<Product>> getProductByShopId(@PathVariable long idShop) {
        return new ResponseEntity<>(productService.getProductByShopId(idShop), HttpStatus.OK);
    }
    //14
    @PostMapping("/{idShop}/products/create")
    public ResponseEntity<Product> saveProductByShopId(@PathVariable long idShop, @RequestBody Product product) {
        Shop shop = shopService.findShopById(idShop);
        product.setShop(shop);
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    //15, 16, 17, 18
    @PostMapping("/{idShop}/products/{idProduct}")
    public ResponseEntity<Product> updateProductByShopId(@PathVariable long idShop, @PathVariable long idProduct, @RequestBody Product product) {
        productService.edit(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    //19
    @GetMapping("/{idShop}/products/{idProduct}")
    public ResponseEntity<Product> findProductByShopId(@PathVariable long idShop, @PathVariable long idProduct) {
        Product product = productService.findProductByShopId(idShop, idProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }




}
