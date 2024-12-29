package com.ms2Desafio03.ms2Desafio03.service;

import com.ms2Desafio03.ms2Desafio03.client.OpenFeignMs1;
import com.ms2Desafio03.ms2Desafio03.entity.Event;
import com.ms2Desafio03.ms2Desafio03.entity.HasTickets;
import com.ms2Desafio03.ms2Desafio03.entity.Ticket;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketCreatDto;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketResponseDto;
import com.ms2Desafio03.ms2Desafio03.mapper.TicketMapper;
import com.ms2Desafio03.ms2Desafio03.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketServices {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private OpenFeignMs1 openFeignMs1;

    @Transactional
    public Ticket save(Ticket ticket){
        Event event = openFeignMs1.getById(ticket.getEvent().getId());

        TicketResponseDto ticketResponseDto = new TicketResponseDto();

        ticketResponseDto.setCpf(ticket.getCpf());
        ticketResponseDto.setCustomerName(ticket.getCustomerName());
        ticketResponseDto.setCustomerMail(ticket.getCustomerMail());
        ticketResponseDto.setEvent(event);
        ticketResponseDto.setBrlTotalAmount(ticket.getBrlTotalAmount());
        ticketResponseDto.setUsdTotalAmount(ticket.getUsdTotalAmount());
        ticketResponseDto.setStatus("concluído");

        Ticket ticketSaved = ticketRepository.save(TicketMapper.toTicket(ticketResponseDto));

        ticketResponseDto.setId(ticketSaved.getId());

        return TicketMapper.toTicket(ticketResponseDto);
    }

    @Transactional
    public Ticket getById(String id){
        return ticketRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Id não encontrado")
        );
    }

    @Transactional
    public HasTickets hasTickets(String eventId){
        Event event = openFeignMs1.getById(eventId);

        List<Ticket> tickets = ticketRepository.findAll();

        HasTickets hasTickets = new HasTickets();

        tickets.forEach(t -> {
            if(t.getEvent().getId().equals(event.getId())){
                hasTickets.setId(event.getId());
                hasTickets.setHasTickets(true);
            }else {
                hasTickets.setId(event.getId());
                hasTickets.setHasTickets(false);
            }
        });

        return hasTickets;
    }

    @Transactional
    public Ticket upDateById(String id, TicketCreatDto ticketCreatDto){
        Ticket ticketToUpdate = getById(id);
        Event event = openFeignMs1.getById(ticketCreatDto.getEventId());

        ticketToUpdate.setCustomerName(ticketCreatDto.getCustomerName());
        ticketToUpdate.setCustomerMail(ticketCreatDto.getCustomerMail());
        ticketToUpdate.setCpf(ticketCreatDto.getCpf());
        ticketToUpdate.setEvent(event);
        ticketToUpdate.setBrlTotalAmount(ticketCreatDto.getBrlTotalAmount());
        ticketToUpdate.setUsdTotalAmount(ticketCreatDto.getUsdTotalAmount());
        ticketToUpdate.setStatus("concluído");

        ticketRepository.save(ticketToUpdate);

        return ticketToUpdate;
    }

    @Transactional
    public void deleteById(String id){
        ticketRepository.deleteById(id);
    }
}
