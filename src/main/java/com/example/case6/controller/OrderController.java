package com.example.case6.controller;

import com.example.case6.model.Order;
import com.example.case6.service.IOrderDetailService;
import com.example.case6.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IOrderDetailService orderDetailService;
    @GetMapping("/{idOrder}")
    public ResponseEntity<Order> findById(@PathVariable long idOrder) {
        return new ResponseEntity<>(orderService.findById(idOrder), HttpStatus.OK);
    }
    @PostMapping("/{idOrder}")
    public ResponseEntity<Order> update(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.update(order), HttpStatus.OK);
    }
    @GetMapping("/delete/{idOrder}")
    public ResponseEntity<Order> delete(@PathVariable long idOrder) {
        Order order = orderService.findById(idOrder);
        orderDetailService.deleteByOrderId(idOrder);
        orderService.deleteById(idOrder);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
