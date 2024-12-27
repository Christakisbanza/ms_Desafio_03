package com.ms1Desafio03.ms1Desafio03.mapper;

import com.ms1Desafio03.ms1Desafio03.entity.Event;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventCreateDto;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventResponseDto;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventUpdateDto;
import org.modelmapper.ModelMapper;

import java.util.List;

public class EventMapper {
    public static Event toEvent(EventCreateDto eventCreateDto){
        return new ModelMapper().map(eventCreateDto, Event.class);
    }

    public static Event toEvent(EventResponseDto responseDto){
        return new ModelMapper().map(responseDto, Event.class);
    }

    public static Event toEvent(EventUpdateDto eventUpdateDto){
        return new ModelMapper().map(eventUpdateDto, Event.class);
    }

    public static EventResponseDto toDto(Event post){
        return new ModelMapper().map(post, EventResponseDto.class);
    }

    public static List<EventResponseDto> toDtos(List<Event> events){
        return events.stream().map(e -> new ModelMapper().map(e, EventResponseDto.class)).toList();
    }

}
