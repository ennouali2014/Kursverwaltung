package com.example.kursverwaltung.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service

public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to,String body,String subject){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("fatimazahraeennouali@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);
        javaMailSender.send(mailMessage);
        System.out.println("MAil sendet");
    }
}
