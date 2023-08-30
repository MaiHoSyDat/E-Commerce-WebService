package com.example.case6.controller.shop;

import com.example.case6.model.Product;
import com.example.case6.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductShopController {

    @Autowired
    private IProductService iProductService;

    @Value("${upload-path}")
    private String upload;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product,
                                           @RequestPart MultipartFile image) {
        String imgPath;
        try {
            if(image != null && !image.isEmpty()) {
                imgPath = image.getOriginalFilename();
                FileCopyUtils.copy(image.getBytes(), new File(upload + imgPath));
                product.setThumbnail("/image/" + imgPath);
            } else {
                product.setThumbnail("/image/default.jpg");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        iProductService.save(product);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
                                           @RequestBody Product product,
                                           @RequestPart MultipartFile image) {
        Optional<Product> productOptional = iProductService.findById(id);
        if(productOptional.isPresent()) {
            String imgPath;
            try {
                if(image != null && !image.isEmpty()) {
                    imgPath = image.getOriginalFilename();
                    FileCopyUtils.copy(image.getBytes(), new File(upload + imgPath));
                    product.setThumbnail("/image/" + imgPath);
                } else {
                    product.setThumbnail(productOptional.get().getThumbnail());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            product.setId(id);
            iProductService.save(product);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Product>> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = iProductService.findById(id);
        if (productOptional.isPresent()) {
            iProductService.delete(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
