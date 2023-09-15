package com.example.case6.controller.Image;

import com.example.case6.model.Image;
import com.example.case6.model.dto.ProductReviewDTO;
import com.example.case6.repository.IImageRepo;
import com.example.case6.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/shops")
public class ImageController {
    @Autowired
    IImageService iImageService;

    @GetMapping("/image/{id}")
    public ResponseEntity<List<Image>> getAllImageByProductId(@PathVariable long id) {
        return new ResponseEntity<>(iImageService.getAllImageByIdProduct(id), HttpStatus.OK);

    }
}
