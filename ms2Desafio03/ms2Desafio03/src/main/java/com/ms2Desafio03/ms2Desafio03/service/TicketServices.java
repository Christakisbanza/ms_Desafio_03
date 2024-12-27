package com.ms2Desafio03.ms2Desafio03.service;

import com.ms2Desafio03.ms2Desafio03.client.OpenFeignMs1;
import com.ms2Desafio03.ms2Desafio03.entity.Event;
import com.ms2Desafio03.ms2Desafio03.entity.Ticket;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketCreatDto;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketResponseDto;
import com.ms2Desafio03.ms2Desafio03.mapper.TicketMapper;
import com.ms2Desafio03.ms2Desafio03.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketServices {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private OpenFeignMs1 openFeignMs1;

    @Transactional
    public Ticket save(TicketCreatDto ticketCreatDto){
        Event event = openFeignMs1.getById(ticketCreatDto.getEventId());

        TicketResponseDto ticketResponseDto = new TicketResponseDto();

        ticketResponseDto.setId(new Ticket().getId());
        ticketResponseDto.setCpf(ticketCreatDto.getCpf());
        ticketResponseDto.setCustomerName(ticketCreatDto.getCustomerName());
        ticketResponseDto.setCustomerMail(ticketCreatDto.getCustomerMail());
        ticketResponseDto.setEvent(event);
        ticketResponseDto.setBrlTotalAmount(ticketCreatDto.getBrlTotalAmount());
        ticketResponseDto.setUsdTotalAmount(ticketCreatDto.getUsdTotalAmount());
        ticketResponseDto.setStatus("concluído");

        ticketRepository.save(TicketMapper.toTicket(ticketResponseDto));

        return TicketMapper.toTicket(ticketResponseDto);
    }

    @Transactional
    public Ticket getById(String id){
        return ticketRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Id não encontrado")
        );
    }
}
