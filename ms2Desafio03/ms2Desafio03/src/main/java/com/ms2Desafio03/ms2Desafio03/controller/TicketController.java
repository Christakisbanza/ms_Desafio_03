package com.ms2Desafio03.ms2Desafio03.controller;

import com.ms2Desafio03.ms2Desafio03.service.TicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ticket/v1")
public class TicketController {

    @Autowired
    private TicketServices ticketServices;

}
