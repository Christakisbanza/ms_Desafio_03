package com.ms2Desafio03.ms2Desafio03.service;

import com.ms2Desafio03.ms2Desafio03.client.OpenFeignMs1;
import com.ms2Desafio03.ms2Desafio03.entity.model.Event;
import com.ms2Desafio03.ms2Desafio03.entity.model.HasTickets;
import com.ms2Desafio03.ms2Desafio03.entity.Ticket;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketCreateDto;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketResponseDto;
import com.ms2Desafio03.ms2Desafio03.exception.DataInvalidException;
import com.ms2Desafio03.ms2Desafio03.exception.EntityNotFoundException;
import com.ms2Desafio03.ms2Desafio03.exception.TicketNotFoundException;
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
        try {
            Event event = openFeignMs1.getById(ticket.getEvent().getId());

            TicketResponseDto ticketResponseDto = new TicketResponseDto();

            if(!ticket.getEvent().getEventName().equals(event.getEventName())){
                throw new RuntimeException();
            }

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
        catch (RuntimeException e){
            throw new DataInvalidException("argumentNotValid: event name or event id invalid");
        }
    }

    @Transactional
    public Ticket getById(String id){
        return ticketRepository.findById(id).orElseThrow(
                () -> new TicketNotFoundException("Id não encontrado")
        );
    }

    @Transactional
    public List<Ticket> getAll(){
        return ticketRepository.findAll();
    }

    @Transactional
    public HasTickets hasTickets(String eventId){
        try {
            Event event = openFeignMs1.getById(eventId);

            List<Ticket> tickets = ticketRepository.findAll();

            HasTickets hasTickets = new HasTickets();

            tickets.forEach(t -> {
                if (t.getEvent().getId().equals(event.getId())) {
                    hasTickets.setId(event.getId());
                    hasTickets.setHasTickets(true);
                } else {
                    hasTickets.setId(event.getId());
                    hasTickets.setHasTickets(false);
                }
            });

            return hasTickets;
        }
        catch (RuntimeException e){
            throw new EntityNotFoundException("id invalid");
        }
    }

    @Transactional
    public Ticket upDateById(String id, TicketCreateDto ticketCreatDto){
        try {
            Ticket ticketToUpdate = getById(id);
            Event event = openFeignMs1.getById(ticketCreatDto.getEventId());

            if(!ticketCreatDto.getEventName().equals(event.getEventName())){
                throw new RuntimeException();
            }

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
        catch (RuntimeException e){
            throw new EntityNotFoundException("argumentNotValid: event name or event ids invalid");
        }
    }

    @Transactional
    public void deleteById(String id){
        try {
            ticketRepository.deleteById(id);
        }
        catch (RuntimeException e){
            throw new TicketNotFoundException("Id not found or invalid");
        }
    }
}
