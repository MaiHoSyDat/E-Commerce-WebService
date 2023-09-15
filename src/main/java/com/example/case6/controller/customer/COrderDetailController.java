package com.example.case6.controller.customer;

import com.example.case6.model.Order;
import com.example.case6.model.OrderDetail;
import com.example.case6.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class COrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;
    @GetMapping("/orderDetail/{idOrder}")
    public ResponseEntity<List<OrderDetail>> getAllOrdersByCustomerId(@PathVariable long idOrder) {
        return new ResponseEntity<>(orderDetailService.getAllOrdersDetailByOrderId(idOrder), HttpStatus.OK);
    }
}
