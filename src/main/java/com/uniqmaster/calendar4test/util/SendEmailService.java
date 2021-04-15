package com.uniqmaster.calendar4test.util;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class SendEmailService {

    /*private final JavaMailSender javaMailSender;

    public SendEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }*/

    public void sendMail(String email, String message) {
        /*System.out.println("Email:"+email + "    "+"message:"+message);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Testing For Calendar project");
        msg.setText(message);
        javaMailSender.send(msg);*/
    }
}
