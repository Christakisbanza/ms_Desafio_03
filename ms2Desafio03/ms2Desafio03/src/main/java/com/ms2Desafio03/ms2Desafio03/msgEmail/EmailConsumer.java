package com.ms2Desafio03.ms2Desafio03.msgEmail;

import com.ms2Desafio03.ms2Desafio03.entity.model.Email;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload Email email){
        System.out.println(email);
    }

}
