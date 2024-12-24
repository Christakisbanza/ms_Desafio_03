package com.ms1Desafio03.ms1Desafio03.controller;

import com.ms1Desafio03.ms1Desafio03.entity.dto.EventCreateDto;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventResponseDto;
import com.ms1Desafio03.ms1Desafio03.mapper.EventMapper;
import com.ms1Desafio03.ms1Desafio03.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("events/v1")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDto> save(@RequestBody EventCreateDto eventCreateDto){
        EventResponseDto responseDto = eventService.save(EventMapper.toEvent(eventCreateDto));
        return ResponseEntity.ok().body(responseDto);
    }
}
