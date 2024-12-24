package com.ms1Desafio03.ms1Desafio03.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "events")
public class Event implements Serializable {

    @Id
    private Long id;
    private String eventName;
    private String dateTime;
    private String cep;

    public Event(Long id, String eventName, String dateTime, String cep) {
        this.id = id;
        this.eventName = eventName;
        this.dateTime = dateTime;
        this.cep = cep;
    }

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
