package com.ms1Desafio03.ms1Desafio03.controller;

import com.ms1Desafio03.ms1Desafio03.entity.Event;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventCreateDto;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventResponseDto;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventUpdateDto;
import com.ms1Desafio03.ms1Desafio03.mapper.EventMapper;
import com.ms1Desafio03.ms1Desafio03.service.EventService;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("events/v1")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create-event")
    public ResponseEntity<EventResponseDto> save(@RequestBody EventCreateDto eventCreateDto){
        Event event = eventService.save(EventMapper.toEvent(eventCreateDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(EventMapper.toDto(event));
    }

    @GetMapping("/get-event/{id}")
    public ResponseEntity<EventResponseDto> getByid(@PathVariable String id){
        Event event = eventService.getById(id);
        return ResponseEntity.ok().body(EventMapper.toDto(event));
    }

    @GetMapping("/get-all-events")
    public ResponseEntity<List<EventResponseDto>> getAll(){
        List<Event> list = eventService.getAll();
        return ResponseEntity.ok().body(EventMapper.toDtos(list));
    }

    @PutMapping("/update-event/{id}")
    public ResponseEntity<EventResponseDto> upDateById(@PathVariable String id, @RequestBody EventUpdateDto eventUpdateDto){
        Event event = eventService.upDateById(id, EventMapper.toEvent(eventUpdateDto));
        return ResponseEntity.ok().body(EventMapper.toDto(event));
    }

    @DeleteMapping("/delete-event/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        eventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
