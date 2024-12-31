package com.ms1Desafio03.ms1Desafio03.consumer;


import com.ms1Desafio03.ms1Desafio03.entity.model.Email;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload Email email){
        email.setStatus("Email recebido !");
        System.out.println(email);
    }

}
