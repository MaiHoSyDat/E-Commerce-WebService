package com.example.case6.service;

public interface IResetPasswordService {
    String getRandomPassword();
    void sendEmailWithNewPassword(String email, String newPassword);
}
