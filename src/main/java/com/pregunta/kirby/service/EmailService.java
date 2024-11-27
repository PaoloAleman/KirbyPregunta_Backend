package com.pregunta.kirby.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(String email, Integer randomNumber) throws MessagingException;
}
