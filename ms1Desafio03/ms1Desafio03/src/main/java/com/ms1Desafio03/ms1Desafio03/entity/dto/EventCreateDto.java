package com.ms1Desafio03.ms1Desafio03.entity.dto;

public class EventCreateDto {

    private String eventName;
    private String dateTime;
    private String cep;

    public EventCreateDto(String eventName, String dateTime, String cep) {
        this.eventName = eventName;
        this.dateTime = dateTime;
        this.cep = cep;
    }

    public EventCreateDto() {
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
