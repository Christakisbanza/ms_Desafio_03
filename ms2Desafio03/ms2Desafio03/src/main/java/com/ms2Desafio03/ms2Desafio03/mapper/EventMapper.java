package com.ms2Desafio03.ms2Desafio03.mapper;

import com.ms2Desafio03.ms2Desafio03.entity.Ticket;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketCreatDto;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;

public class EventMapper {
    public static Ticket toTicket(TicketCreatDto ticketCreatDto){
        return new ModelMapper().map(ticketCreatDto, Ticket.class);
    }

    public static Ticket toTicket(TicketResponseDto responseDto){
        return new ModelMapper().map(responseDto, Ticket.class);
    }

    public static TicketResponseDto toDto(Ticket ticket){
        return new ModelMapper().map(ticket, TicketResponseDto.class);
    }

    public static List<TicketResponseDto> toDtos(List<Ticket> events){
        return events.stream().map(e -> new ModelMapper().map(e, TicketResponseDto.class)).toList();
    }

}
