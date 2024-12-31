package com.ms2Desafio03.ms2Desafio03.entity.model;

public class Email {

    private String from;
    private String to;
    private String subject;
    private String text;
    private String status;

    public Email(String from, String to, String subject, String text, String status) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.status = status;
    }

    public Email() {
    }

    @Override
    public String toString() {
        return "Email{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
