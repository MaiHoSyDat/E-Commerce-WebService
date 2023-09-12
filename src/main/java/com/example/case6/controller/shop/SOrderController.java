package com.example.case6.controller.shop;

import com.example.case6.model.Order;
import com.example.case6.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/shop")
public class SOrderController {
    @Autowired
    private IOrderService orderService;
    @GetMapping("/{idShop}/orders")
    public ResponseEntity<List<Order>> getAllOrdersByShopId(@PathVariable long idShop) {
        return new ResponseEntity<>(orderService.getAllOrdersByShopId(idShop), HttpStatus.OK);
    }

}
