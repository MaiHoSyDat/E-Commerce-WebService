package com.example.case6.controller;

import com.example.case6.model.Account;
import com.example.case6.model.dto.EditPassDTO;
import com.example.case6.model.email.ResetPasswordRequest;
import com.example.case6.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{email}")
    public ResponseEntity<?> getAccountByEmail(@PathVariable String email){
        Account account = iAccountService.getAccountByEmail(email);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {

        String newPassword = iAccountService.getRandomPassword();
        String email = resetPasswordRequest.getEmail();

        Account account = iAccountService.getAccountByEmail(email);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        account.setPassword(newPassword);
        iAccountService.save(account);

        sendEmailWithNewPassword(email, newPassword);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void sendEmailWithNewPassword(String email, String newPassword) {
        String subject = "Requested to reset your password";
        String resetPasswordLink = "http://localhost:3000/signin";
        String emailContent = "Hello,\n\n";
        emailContent += "You have requested to reset your password, we have reset your password to: " + newPassword + "\n";
        emailContent += "Please, enter the following link to continue:" + resetPasswordLink + "\n\n";
        emailContent += "Best Regards,\n";
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("send.email712@gmail.com");
            message.setTo(email);
            message.setSubject(subject);
            message.setText(emailContent);
            emailSender.send(message);
        } catch (MailException e) {
            e.getMessage();
        }
    }

}
