package com.example.case6.service.impl;

import com.example.case6.service.IResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ResetPasswordServiceImpl implements IResetPasswordService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public String getRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

    @Override
    public void sendEmailWithNewPassword(String email, String newPassword) {
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
