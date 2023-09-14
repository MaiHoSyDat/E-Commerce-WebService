package com.example.case6.controller;

import com.example.case6.model.Wishlist;
import com.example.case6.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("wishlist")
public class WishlistController {
    @Autowired
    private IWishlistService wishlistService;
    @GetMapping("/{idCustomer}")
    public ResponseEntity<Wishlist> findWishlistByCustomerId(@PathVariable long idCustomer) {
        System.out.println(idCustomer);
        return new ResponseEntity<>(wishlistService.findWishlistByCustomerId(idCustomer), HttpStatus.OK);
    }
    @PostMapping("/{idWishlist}")
    public ResponseEntity<Wishlist> updateWishlist(@RequestBody Wishlist wishlist) {
        wishlistService.updateWishlist(wishlist);
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }
}
