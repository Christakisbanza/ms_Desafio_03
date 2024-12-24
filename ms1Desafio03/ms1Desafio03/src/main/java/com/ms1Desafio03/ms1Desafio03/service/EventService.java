package com.ms1Desafio03.ms1Desafio03.service;

import com.ms1Desafio03.ms1Desafio03.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
}
