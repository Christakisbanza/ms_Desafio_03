package com.ms1Desafio03.ms1Desafio03.service;

import com.ms1Desafio03.ms1Desafio03.client.OpenFeignCep;
import com.ms1Desafio03.ms1Desafio03.entity.Event;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventResponseDto;
import com.ms1Desafio03.ms1Desafio03.mapper.EventMapper;
import com.ms1Desafio03.ms1Desafio03.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OpenFeignCep openFeignCep;



    @Transactional
    public Event save(Event event){
        EventResponseDto responseDto = openFeignCep.getCepInfo(event.getCep());

        responseDto.setId(event.getId());
        responseDto.setEventName(event.getEventName());
        responseDto.setDateTime(event.getDateTime());
        responseDto.setCep(event.getCep());

        Event eventSaved = eventRepository.save(EventMapper.toEvent(responseDto));

        responseDto.setId(eventSaved.getId());

        return EventMapper.toEvent(responseDto);
    }


    @Transactional
    public Event getById(String id){
        return eventRepository.findById(id).orElseThrow(
                () -> new RuntimeException("ID not found")
        );
    }


}
