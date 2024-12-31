package com.ms2Desafio03.ms2Desafio03.msgEmail;

import com.ms2Desafio03.ms2Desafio03.config.RabbitmqConfig;
import com.ms2Desafio03.ms2Desafio03.entity.Ticket;
import com.ms2Desafio03.ms2Desafio03.entity.model.Email;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MsgEmailProducer {

    private final RabbitTemplate rabbitTemplate;

    public MsgEmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEmailMsg(Ticket ticket){
        Email emailMsg = new Email();
        emailMsg.setFrom(ticket.getEvent().getEventName());
        emailMsg.setTo(ticket.getCustomerMail());
        emailMsg.setSubject("Compra de ingreço para o show");
        emailMsg.setText("Sua compra foi efetuado com sucesso, obrigado e volte sempre !");
        emailMsg.setStatus("Enviado com sucesso !");
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, RabbitmqConfig.KEY, emailMsg);
        System.out.println(emailMsg.getStatus());
    }
}
