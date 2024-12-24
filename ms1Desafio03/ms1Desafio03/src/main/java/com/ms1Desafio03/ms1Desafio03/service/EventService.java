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
    public EventResponseDto save(Event event){

        eventRepository.save(event);

        EventResponseDto responseDto = openFeignCep.getCepInfo();

        responseDto.setEventName(event.getEventName());
        responseDto.setDateTime(event.getDateTime());

        return responseDto;
    }


}
