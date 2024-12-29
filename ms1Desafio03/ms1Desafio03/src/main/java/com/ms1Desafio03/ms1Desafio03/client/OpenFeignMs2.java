package com.ms1Desafio03.ms1Desafio03.client;

import com.ms1Desafio03.ms1Desafio03.entity.HasTickets;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "consultaMs2", url = "http://localhost:8081/tickets/v1")
public interface OpenFeignMs2 {

    @GetMapping("/check-tickets-by-event/{eventId}")
    HasTickets hasTickets(@PathVariable String eventId );
}
