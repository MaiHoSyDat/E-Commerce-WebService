package com.example.case6.controller;

import com.example.case6.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.case6.service.impl.AccountServiceImpl;

@RestController
@CrossOrigin("*")
public class SendEmailController {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private IAccountService iAccountService;

//    @PostMapping("/send-email/{email}")
//    public String sendEmail(@PathVariable String email) {
//        String newPassword = iAccountService.getRandomPassword();
//        String subject = "Requested to reset your password";
//        String resetPasswordLink = "http://localhost:3000/signin";
//        String emailContent = "Hello,\n\n";
//        emailContent += "You have requested to reset your password, we have reset your password to: " + newPassword + "\n";
//        emailContent += "Please, enter the following link to continue:" + resetPasswordLink + "\n\n";
//        emailContent += "Best Regards,\n";
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("send.email712@gmail.com");
//            message.setTo(email);
//            message.setSubject(subject);
//            message.setText(emailContent);
//            emailSender.send(message);
//            return "Email sent successfully!";
//        } catch (MailException e) {
//            return "Failed to send email: " + e.getMessage();
//        }
//    }
}
