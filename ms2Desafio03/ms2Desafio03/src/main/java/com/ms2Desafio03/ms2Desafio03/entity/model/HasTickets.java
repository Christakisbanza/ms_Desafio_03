package com.ms2Desafio03.ms2Desafio03.entity.model;

public class HasTickets {

    private String id;
    private boolean hasTickets;

    public HasTickets() {
    }

    public HasTickets(String id, boolean hasTickets) {
        this.id = id;
        this.hasTickets = hasTickets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasTickets() {
        return hasTickets;
    }

    public void setHasTickets(boolean hasTickets) {
        this.hasTickets = hasTickets;
    }
}
