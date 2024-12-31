package com.ms1Desafio03.ms1Desafio03.service;

import com.ms1Desafio03.ms1Desafio03.entity.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendEmailService {

    private final JavaMailSender javaMailSender;

    public SendEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public void sendEmail(Email email){
        try{
            email.setFrom(emailFrom);

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setTo(email.getTo());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getText());

            javaMailSender.send(mailMessage);

            email.setStatus("Email recebido !");
        }
        catch (MailException e){
            email.setStatus("Email não recebido");
        }
    }
}
