package com.example.case6.controller;

import com.example.case6.model.Account;
import com.example.case6.model.Cart;
import com.example.case6.model.CartDetail;
import com.example.case6.model.Product;
import com.example.case6.service.IAccountService;
import com.example.case6.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("cart")
public class CartController {
    @Autowired
    ICartService iCartService;
    @Autowired
    IAccountService accountService;

    @GetMapping()
    public ResponseEntity<List<CartDetail>> getAllCartDetail() {
        Account account = accountService.getById(1);
        return new ResponseEntity<>(iCartService.getAllCartDetail(account), HttpStatus.OK);
    }

    // <37> Thêm sản phẩm vào giỏ hàng
    @PostMapping("/addToCart")
    public ResponseEntity<Cart> addToCart(@RequestParam long productId, @RequestParam int quantity) {
        Account account = accountService.getById(1);
        return new ResponseEntity<>(iCartService.addToCart(account, productId, quantity), HttpStatus.OK);
    }

    // <38> Cập nhật số lượng trong giỏ hàng, FE gửi đến là một List<CartDetail>
    @PostMapping("/updateCart")
    public ResponseEntity<?> updateCart(@RequestBody List<CartDetail> cartDetails) {
        iCartService.updateCart(cartDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // <39>  Xóa 1 sản phẩm trong giỏ hàng, FE gửi đến ID của CartDetail ;
    @PostMapping("/deleteProductByCart")
    public ResponseEntity<?> deleteProductByCart(@RequestParam long cartDetailId) {
        iCartService.deleteProductByCar(cartDetailId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

