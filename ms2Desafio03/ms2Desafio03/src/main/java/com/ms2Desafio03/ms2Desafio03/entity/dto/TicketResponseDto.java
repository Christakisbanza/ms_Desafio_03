package com.ms2Desafio03.ms2Desafio03.entity.dto;

import com.ms2Desafio03.ms2Desafio03.entity.Event;

public class TicketResponseDto {

    private String id;
    private  String cpf;
    private String customerName;
    private String customerMail;
    private Event event;
    private String BRLtotalAmount;
    private String USDtotalAmount;
    private String status;

    public TicketResponseDto(String id, String cpf, String customerName, String customerMail, Event event, String BRLtotalAmount, String USDtotalAmount, String status) {
        this.id = id;
        this.cpf = cpf;
        this.customerName = customerName;
        this.customerMail = customerMail;
        this.event = event;
        this.BRLtotalAmount = BRLtotalAmount;
        this.USDtotalAmount = USDtotalAmount;
        this.status = status;
    }

    public TicketResponseDto() {
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

    public String getBRLtotalAmount() {
        return BRLtotalAmount;
    }

    public void setBRLtotalAmount(String BRLtotalAmount) {
        this.BRLtotalAmount = BRLtotalAmount;
    }

    public String getUSDtotalAmount() {
        return USDtotalAmount;
    }

    public void setUSDtotalAmount(String USDtotalAmount) {
        this.USDtotalAmount = USDtotalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
