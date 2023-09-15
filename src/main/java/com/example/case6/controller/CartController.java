package com.example.case6.controller;

import com.example.case6.model.Account;
import com.example.case6.model.Cart;
import com.example.case6.model.CartDetail;
import com.example.case6.model.dto.CodeDTO;
import com.example.case6.model.dto.ShopCodeDTO;
import com.example.case6.service.IAccountService;
import com.example.case6.service.ICartService;
import com.example.case6.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    IAccountService iAccountService;
    @Autowired
    IShopService iShopService;

    @GetMapping()
    public ResponseEntity<List<CartDetail>> getAllCartDetail() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountService.getAccountByUsername(userDetails.getUsername());
        return new ResponseEntity<>(iCartService.getAllCartDetail(account), HttpStatus.OK);
    }

    // <37> Thêm sản phẩm vào giỏ hàng
    @PostMapping("/addToCart")
    public ResponseEntity<CartDetail> addToCart(@RequestParam long productId, @RequestParam int quantity) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountService.getAccountByUsername(userDetails.getUsername());
        return new ResponseEntity<>(iCartService.addToCart(account, productId, quantity), HttpStatus.OK);
    }

    // <38> Cập nhật số lượng trong giỏ hàng, FE gửi đến là một List<CartDetail>
    @PostMapping("/updateCart")
    public ResponseEntity<List<CartDetail>> updateCart(@RequestBody List<CartDetail> cartDetails) {
        return new ResponseEntity<>(iCartService.updateCart(cartDetails), HttpStatus.OK);
    }

    // <39>  Xóa 1 sản phẩm trong giỏ hàng, FE gửi đến ID của CartDetail ;
    @PostMapping("/deleteProductByCart")
    public ResponseEntity<Long> deleteProductByCart(@RequestParam long cartDetailId) {
        iCartService.deleteProductByCar(cartDetailId);
        return new ResponseEntity<>(cartDetailId, HttpStatus.OK);
    }

    @GetMapping("/shopCode")
    public ResponseEntity<List<ShopCodeDTO>> getAllShopCode() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountService.getAccountByUsername(userDetails.getUsername());
        return new ResponseEntity<>(iShopService.getAllShopCode(account), HttpStatus.OK);
    }

    // Thanh toán;
    @PostMapping("/payment/{fullName}/{phone}/{address}")
    public ResponseEntity<?> payment(@PathVariable String fullName, @PathVariable String phone, @PathVariable String address, @RequestBody List<CodeDTO> codeDTOs) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountService.getAccountByUsername(userDetails.getUsername());
        if (iCartService.payment(account, fullName, phone, address, codeDTOs)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

