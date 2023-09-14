package com.example.case6.controller.customer;

import com.example.case6.model.Order;
import com.example.case6.model.Product;
import com.example.case6.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class COrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("/{idCustomer}/orders")
    public ResponseEntity<List<Order>> getAllOrdersByCustomerId(@PathVariable long idCustomer) {
        return new ResponseEntity<>(orderService.getAllOrdersByCustomerId(idCustomer), HttpStatus.OK);
    }

}
