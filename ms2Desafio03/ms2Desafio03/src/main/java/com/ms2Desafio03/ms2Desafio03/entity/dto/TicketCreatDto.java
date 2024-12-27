package com.ms2Desafio03.ms2Desafio03.entity.dto;

public class TicketCreatDto {

    private String customerName;
    private String cpf;
    private String customerMail;
    private String eventId;
    private String eventName;
    private String brlTotalAmount;
    private String usdTotalAmount;

    public TicketCreatDto(String customerName, String cpf, String customerMail, String eventId, String eventName, String brlTotalAmount, String usdTotalAmount) {
        this.customerName = customerName;
        this.cpf = cpf;
        this.customerMail = customerMail;
        this.eventId = eventId;
        this.eventName = eventName;
        this.brlTotalAmount = brlTotalAmount;
        this.usdTotalAmount = usdTotalAmount;
    }

    public TicketCreatDto() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
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
}
