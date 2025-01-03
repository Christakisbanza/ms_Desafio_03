package com.ms2Desafio03.ms2Desafio03.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TicketCreateDto {

    @NotBlank
    @Size(min = 3, max = 50)
    private String customerName;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank
    @Email
    private String customerMail;

    @NotBlank
    private String eventId;

    @NotBlank
    private String eventName;

    @NotBlank
    private String brlTotalAmount;

    @NotBlank
    private String usdTotalAmount;

    public TicketCreateDto(String customerName, String cpf, String customerMail, String eventId, String eventName, String brlTotalAmount, String usdTotalAmount) {
        this.customerName = customerName;
        this.cpf = cpf;
        this.customerMail = customerMail;
        this.eventId = eventId;
        this.eventName = eventName;
        this.brlTotalAmount = brlTotalAmount;
        this.usdTotalAmount = usdTotalAmount;
    }

    public TicketCreateDto() {
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
