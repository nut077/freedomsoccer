package com.freedomsoccer.service;

import com.freedomsoccer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendNotification(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("nuttapon.tavee@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Welcome to freedomsoccer");
        StringBuilder text = new StringBuilder();
        text.append("Welcome ").append(user.getFirstName()).append(" ").append(user.getLastName()).append(" to freedomsoccer wish you happy\n");
        text.append("username: ").append(user.getUsername()).append("\n");
        text.append("password: ").append(user.getPassword()).append("\n");
        mailMessage.setText(text.toString());
        javaMailSender.send(mailMessage);
    }
}
