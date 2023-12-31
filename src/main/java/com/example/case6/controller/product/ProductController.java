package com.example.case6.controller.product;

import com.example.case6.model.Product;
import com.example.case6.model.dto.FilterProductDTO;
import com.example.case6.model.dto.ProductDTO;
import com.example.case6.model.dto.ProductReviewDTO;
import com.example.case6.model.dto.ReviewDTO;
import com.example.case6.service.IProductService;
import com.example.case6.service.IReviewSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService iProductService;
    @Autowired
    private IReviewSevice iReviewSevice;


    @GetMapping("/page")
    public ResponseEntity<Page<Product>> findAllProduct(@PageableDefault (size = 12) Pageable pageable) {
        Page<Product> page= iProductService.getAllProduct(pageable);
        System.out.println(page);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        return new ResponseEntity<>(iProductService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO productDTO = iProductService.findByIdDto(id);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
    @GetMapping("/review/{id}")
    public ResponseEntity<List<ReviewDTO>> getProductDetail(@PathVariable Long id) {
        List<ReviewDTO> reviewDTOS =  iReviewSevice.getAllReviewByIdProduct(id);
        iReviewSevice.getTotalReviewRating(id);
        return new ResponseEntity<>(reviewDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        iProductService.save(product);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,
                                           @RequestBody Product product) {
        Optional<Product> productOptional = iProductService.findById(id);
        if (productOptional.isPresent()) {
            product.setId(id);
            iProductService.save(product);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Product does not exist", HttpStatus.NOT_ACCEPTABLE);
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

    //36.KH lọc sp theo nhiều tiêu chí
    @GetMapping("/filter")
    public ResponseEntity<Page<Product>> filter
    (@RequestParam(required = false, defaultValue = "0") Double minPrice,
     @RequestParam(required = false, defaultValue = "999999999") Double maxPrice,
     @RequestParam(required = false, defaultValue = "") String name,
     @RequestParam(required = false, defaultValue = "") String shopName,
     @RequestParam(required = false, defaultValue = "") Double rating,
     @PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(iProductService.filter(minPrice, maxPrice, name, shopName, rating, pageable), HttpStatus.OK);
    }
    @PostMapping("/filter")
    public ResponseEntity<List<ProductReviewDTO>> getFilterProducts(@RequestBody FilterProductDTO filterProductDTO) {
        System.out.println(filterProductDTO);
        return new ResponseEntity<>(iProductService.getFilterProductsDTO(filterProductDTO), HttpStatus.OK);
    }
//    @PostMapping("/filter")
//    public ResponseEntity<FilterProductDTO> getFilterProducts(@RequestBody FilterProductDTO filterProductDTO) {
//        return new ResponseEntity<>(filterProductDTO, HttpStatus.OK);
//    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {
        return new ResponseEntity<>(iProductService.getById(id), HttpStatus.OK);
    }
    @PostMapping("/removeImage/{imageId}")
    public ResponseEntity<?> removeImage(@PathVariable long imageId){
        iProductService.removeImageById(imageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
