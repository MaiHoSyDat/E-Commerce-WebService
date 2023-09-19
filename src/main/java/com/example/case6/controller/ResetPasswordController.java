package com.example.case6.controller;

import com.example.case6.model.Account;
import com.example.case6.model.email.ResetPasswordRequest;
import com.example.case6.service.IAccountService;
import com.example.case6.service.IResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ResetPasswordController {

    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private IResetPasswordService iResetPasswordService;

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {

        String newPassword = iResetPasswordService.getRandomPassword();
        String email = resetPasswordRequest.getEmail();
        Account account = iAccountService.getAccountByEmail(email);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        account.setPassword(newPassword);
        iAccountService.save(account);

        iResetPasswordService.sendEmailWithNewPassword(email, newPassword);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
