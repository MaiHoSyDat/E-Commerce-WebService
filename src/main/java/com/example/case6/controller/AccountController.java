package com.example.case6.controller;

import com.example.case6.model.Account;
import com.example.case6.model.dto.EditPassDTO;
import com.example.case6.model.email.ResetPasswordRequest;
import com.example.case6.service.IAccountService;
import com.example.case6.service.IResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    IAccountService iAccountService;

    @PostMapping("/forgot")
    public ResponseEntity<Account> changePassword(@RequestParam String username, @RequestParam String password){
        return new ResponseEntity<>(iAccountService.changePassword(username,password), HttpStatus.OK);
    }

    @PostMapping("/editPass")
    public ResponseEntity<?> editPass(@RequestBody EditPassDTO editPassDTO){
        if ( iAccountService.editPass(editPassDTO)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/shop/{idShop}")
    public ResponseEntity<Account> getAccountByShopId(@PathVariable long idShop) {
        return new ResponseEntity<>(iAccountService.getAccountByShopId(idShop), HttpStatus.OK);
    }
    @GetMapping("/idAccount/{idFind}")
    public ResponseEntity<List<Long>> getAllIdAccountMapToMessage(@PathVariable long idFind) {
        return new ResponseEntity<>(iAccountService.getAllIdAccountMapToMessage(idFind), HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getAccountByEmail(@PathVariable String email){
        Account account = iAccountService.getAccountByEmail(email);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
