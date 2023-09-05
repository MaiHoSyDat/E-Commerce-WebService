package com.example.case6.controller.product;

import com.example.case6.model.Category;
import com.example.case6.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryControllerAPI {

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return new ResponseEntity<>(iCategoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        iCategoryService.save(category);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
