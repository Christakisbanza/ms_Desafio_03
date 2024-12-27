package com.ms2Desafio03.ms2Desafio03.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "tickets")
public class Ticket implements Serializable {

    @Id
    private String id;
    private  String cpf;
    private String customerName;
    private String customerMail;
    private Event event;
    private String brlTotalAmount;
    private String usdTotalAmount;
    private String status;

    public Ticket(String id, String cpf, String customerName, String customerMail, Event event, String brlTotalAmount, String usdTotalAmount, String status) {
        this.id = id;
        this.cpf = cpf;
        this.customerName = customerName;
        this.customerMail = customerMail;
        this.event = event;
        this.brlTotalAmount = brlTotalAmount;
        this.usdTotalAmount = usdTotalAmount;
        this.status = status;
    }

    public Ticket() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getBrlTotalAmount() {
        return brlTotalAmount;
    }

    public void setBrlTotalAmount(String brlTotalAmount) {
        this.brlTotalAmount = brlTotalAmount;
    }

    public String getUsdTotalAmount() {
        return usdTotalAmount;
    }

    public void setUsdTotalAmount(String usdTotalAmount) {
        this.usdTotalAmount = usdTotalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
