package com.ms2Desafio03.ms2Desafio03.client;

import com.ms2Desafio03.ms2Desafio03.entity.Event;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "consultaMs1", url = "http://localhost:8080/events/v1")
public interface OpenFeignMs1 {

    @GetMapping("/get-event/{id}")
    Event getById(@PathVariable String id);
}
