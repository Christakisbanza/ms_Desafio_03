package com.ms1Desafio03.ms1Desafio03.controller;

import com.ms1Desafio03.ms1Desafio03.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("events/v1")
public class EventController {

    @Autowired
    private EventService eventService;
    
}
