package com.example.case6.controller;

import com.example.case6.model.Code;
import com.example.case6.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("code")
public class CodeController {
    @Autowired
    private ICodeService codeService;
    @GetMapping("/shop/{idShop}")
    public ResponseEntity<List<Code>> getAllCodeByShopId(@PathVariable long idShop) {
        return new ResponseEntity<>(codeService.getAllCodeByShopId(idShop), HttpStatus.OK);
    }
    @PostMapping("/setQuantity/{idCode}")
    public ResponseEntity<Code> setQuantity(@RequestBody Code code) {
        codeService.setQuantity(code);
        return new ResponseEntity<>(code, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Code> saveCode(@RequestBody Code code){
        codeService.saveCode(code);
        return new ResponseEntity<>(code, HttpStatus.OK);

    }
}
