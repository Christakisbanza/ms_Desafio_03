package com.ms1Desafio03.ms1Desafio03.consumer;


import com.ms1Desafio03.ms1Desafio03.entity.model.Email;
import com.ms1Desafio03.ms1Desafio03.service.SendEmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private SendEmailService service;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload Email email){
        service.sendEmail(email);
        System.out.println(email);
    }

}
