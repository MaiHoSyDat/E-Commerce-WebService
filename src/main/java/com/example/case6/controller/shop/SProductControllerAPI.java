package com.example.case6.controller.shop;

import com.example.case6.model.Image;
import com.example.case6.model.Product;
import com.example.case6.model.Shop;
import com.example.case6.model.dto.ProductDTO;
import com.example.case6.repository.IImageRepo;
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
    @Autowired
    private IImageRepo iImageRepo;

    @GetMapping("/{idShop}/products")
    public ResponseEntity<List<Product>> getProductByShopId(@PathVariable long idShop) {
        return new ResponseEntity<>(productService.getProductByShopId(idShop), HttpStatus.OK);
    }

    //14
    @PostMapping("/{idShop}/products/create")
    public ResponseEntity<ProductDTO> saveProductByShopId(@PathVariable long idShop, @RequestBody ProductDTO productDTO) {

        productService.save(productDTO, idShop);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    //15, 16, 17, 18
    @PostMapping("/{idShop}/products/{idProduct}")
    public ResponseEntity<?> updateProductByShopId(@PathVariable long idShop,
                                                   @PathVariable long idProduct,
                                                   @RequestBody Product product) {
        Product productToUpdate = productService.findProductByShopId(idShop, idProduct);
        if (productToUpdate != null) {
            product.setId(idProduct);
            productService.edit(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Product does not exist" , HttpStatus.NOT_ACCEPTABLE);
    }

    //19
    @GetMapping("/{idShop}/products/{idProduct}")
    public ResponseEntity<Product> findProductByShopId(@PathVariable long idShop, @PathVariable long idProduct) {
        Product product = productService.findProductByShopId(idShop, idProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/getImages/{productId}")
    public ResponseEntity<List<Image>> getImageByProductId(@PathVariable long productId) {
        return new ResponseEntity<>(iImageRepo.findAllByProductId(productId), HttpStatus.OK);
    }


}
