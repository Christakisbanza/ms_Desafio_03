package com.ms1Desafio03.ms1Desafio03.mapper;

import com.ms1Desafio03.ms1Desafio03.entity.Event;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventCreateDto;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventResponseDto;
import org.modelmapper.ModelMapper;

public class EventMapper {
    public static Event toEvent(EventCreateDto eventCreateDto){
        return new ModelMapper().map(eventCreateDto, Event.class);
    }

    public static Event toEvent(EventResponseDto responseDto){
        return new ModelMapper().map(responseDto, Event.class);
    }

    public static EventResponseDto toDto(Event post){
        return new ModelMapper().map(post, EventResponseDto.class);
    }

}
