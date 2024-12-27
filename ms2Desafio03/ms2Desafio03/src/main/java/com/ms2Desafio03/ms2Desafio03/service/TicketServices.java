package com.ms2Desafio03.ms2Desafio03.service;

import com.ms2Desafio03.ms2Desafio03.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServices {

    @Autowired
    private TicketRepository ticketRepository;

}
