package com.example.case6.controller.customer;

import com.example.case6.model.Account;
import com.example.case6.model.dto.OrderDTO;
import com.example.case6.service.IAccountService;
import com.example.case6.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class Payment {
    @Autowired
    IAccountService iAccountService;
    @Autowired
    IOrderDetailService iOrderDetailService;

    @GetMapping("/createOrder")
    public void createOrder(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountService.getAccountByUsername(userDetails.getUsername());
        iOrderDetailService.createOrderDetail(account);
    }
    @GetMapping("/unpaidOrders")
    public ResponseEntity<List<OrderDTO>> getUnpaidOrders(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = iAccountService.getAccountByUsername(userDetails.getUsername());
        return new ResponseEntity<>( iOrderDetailService.getOrderByUnpaid(account),HttpStatus.OK);
    }
}
