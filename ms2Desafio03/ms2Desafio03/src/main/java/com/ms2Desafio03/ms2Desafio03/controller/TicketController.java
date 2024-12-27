package com.ms2Desafio03.ms2Desafio03.controller;

import com.ms2Desafio03.ms2Desafio03.entity.Ticket;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketCreatDto;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketResponseDto;
import com.ms2Desafio03.ms2Desafio03.mapper.TicketMapper;
import com.ms2Desafio03.ms2Desafio03.service.TicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tickets/v1")
public class TicketController {

    @Autowired
    private TicketServices ticketServices;

    @PostMapping("/create-ticket")
    public ResponseEntity<TicketResponseDto> save(@RequestBody TicketCreatDto ticketCreatDto){
        Ticket ticket = ticketServices.save(ticketCreatDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(TicketMapper.toDto(ticket));
    }

}
