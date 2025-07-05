package com.example.bank_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class emailService {

    @Autowired
    private JavaMailSender jms;




    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmail(String mail) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(sender);
        smm.setTo(mail);
        smm.setSubject("Thanks for using rolex bank");
        String username[]=mail.split("@");
        String msg="Hii "+username[0]+" "+"you successfully registered in rolex bank. You can now login to continue";
        smm.setText(msg);

        jms.send(smm);
    }
}

