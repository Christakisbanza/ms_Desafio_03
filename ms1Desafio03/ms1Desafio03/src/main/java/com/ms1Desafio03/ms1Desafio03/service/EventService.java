package com.ms1Desafio03.ms1Desafio03.service;

import com.ms1Desafio03.ms1Desafio03.client.OpenFeignCep;
import com.ms1Desafio03.ms1Desafio03.client.OpenFeignMs2;
import com.ms1Desafio03.ms1Desafio03.entity.Event;
import com.ms1Desafio03.ms1Desafio03.entity.HasTickets;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventResponseDto;
import com.ms1Desafio03.ms1Desafio03.exception.ArgsNotValidException;
import com.ms1Desafio03.ms1Desafio03.exception.EntityNotFoundException;
import com.ms1Desafio03.ms1Desafio03.exception.EventNotFoundException;
import com.ms1Desafio03.ms1Desafio03.mapper.EventMapper;
import com.ms1Desafio03.ms1Desafio03.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OpenFeignCep openFeignCep;

    @Autowired
    private OpenFeignMs2 openFeignMs2;



    @Transactional
    public Event save(Event event){
        try {
            EventResponseDto responseDto = openFeignCep.getCepInfo(event.getCep());

            responseDto.setEventName(event.getEventName());
            responseDto.setDateTime(event.getDateTime());
            responseDto.setCep(event.getCep());

            Event eventSaved = eventRepository.save(EventMapper.toEvent(responseDto));

            responseDto.setId(eventSaved.getId());

            return EventMapper.toEvent(responseDto);
        }
        catch (RuntimeException e){
            throw new ArgsNotValidException("CEP not found or invalid");
        }
    }


    @Transactional
    public Event getById(String id){
        return eventRepository.findById(id).orElseThrow(
                () -> new EventNotFoundException("ID not found or invalid")
        );
    }

    @Transactional
    public List<Event> getAll(){
        return eventRepository.findAll();
    }

    @Transactional
    public List<Event> getAllAsc(){
        return eventRepository.findAllByOrderByEventNameAsc();
    }

    @Transactional
    public Event upDateById(String id, Event event){
        try {
            Event eventToUpdate = getById(id);
            EventResponseDto responseDto = openFeignCep.getCepInfo(event.getCep());

            eventToUpdate.setEventName(event.getEventName());
            eventToUpdate.setDateTime(event.getDateTime());
            eventToUpdate.setCep(event.getCep());

            eventToUpdate.setLogradouro(responseDto.getLogradouro());
            eventToUpdate.setBairro(responseDto.getBairro());
            eventToUpdate.setLocalidade(responseDto.getLocalidade());
            eventToUpdate.setUf(responseDto.getUf());

            eventRepository.save(eventToUpdate);

            return eventToUpdate;
        }
        catch (RuntimeException e){
            throw new EntityNotFoundException("CEP or ID event invalid");
        }
    }

    @Transactional
    public void deleteById(String id){
        try {
            HasTickets hasTickets = openFeignMs2.hasTickets(id);

            if (hasTickets.isHasTickets()) {
                throw new RuntimeException();
            } else {
                eventRepository.deleteById(id);
            }
        }
        catch (RuntimeException e){
            throw new EntityNotFoundException("O evento não pode ser deletado porque possui ingressos vendidos ou ID inválido");
        }
    }


}
