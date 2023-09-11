package com.example.case6.controller.shop;

import com.example.case6.model.Shop;
import com.example.case6.model.dto.ShopReviewDTO;
import com.example.case6.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/shops")
public class SShopControllerAPI {
    @Autowired
    private IShopService shopService;
    @GetMapping
    public ResponseEntity<List<Shop>> getAllShop () {
        return new ResponseEntity<>(shopService.getAllShop(), HttpStatus.OK);
    }
    @GetMapping("/page/{offset}")
    public ResponseEntity<List<Shop>> getFiveShopsPage (@PathVariable int offset) {
        return new ResponseEntity<>(shopService.getFiveShopsPage(offset), HttpStatus.OK);
    }
    //12
    @GetMapping("/{idShop}")
    public ResponseEntity<Shop> findShopById (@PathVariable long idShop) {
        return new ResponseEntity<>(shopService.findShopById(idShop), HttpStatus.OK);
    }
    //10
    @PostMapping
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        shopService.saveShop(shop);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }
    //13
    @PostMapping("/{idShop}")
    public ResponseEntity<Shop> updateShop(@RequestBody Shop shop) {
        shopService.editShop(shop);
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }
    // tìm shop theo account login
    @GetMapping("/login/{idAccount}")
    public ResponseEntity<Shop> getShopByAccountLogin (@PathVariable long idAccount) {
        return new ResponseEntity<>(shopService.getShopByAccountLogin(idAccount), HttpStatus.OK);
    }
    @GetMapping("/dto/{idShop}")
    public ResponseEntity<ShopReviewDTO> findShopDTO (@PathVariable long idShop) {
        return new ResponseEntity<>(shopService.findShopDTO(idShop), HttpStatus.OK);
    }
    @GetMapping("/login/dto/{idAccount}")
    public ResponseEntity<ShopReviewDTO> findShopDTOByAccountLogin (@PathVariable long idAccount) {
        return new ResponseEntity<>(shopService.findShopDTO(idAccount), HttpStatus.OK);
    }
}
