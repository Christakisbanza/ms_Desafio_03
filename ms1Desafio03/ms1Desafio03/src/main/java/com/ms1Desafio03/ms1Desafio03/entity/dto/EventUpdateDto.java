package com.ms1Desafio03.ms1Desafio03.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EventUpdateDto {

    @NotBlank
    @Size(min = 3, max = 50)
    private String eventName;

    @NotBlank
    private String dateTime;

    @NotBlank
    private String cep;

    public EventUpdateDto(String eventName, String dateTime, String cep) {
        this.eventName = eventName;
        this.dateTime = dateTime;
        this.cep = cep;
    }

    public EventUpdateDto() {
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
