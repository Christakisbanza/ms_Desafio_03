package com.ms1Desafio03.ms1Desafio03.client;

import com.ms1Desafio03.ms1Desafio03.entity.dto.EventResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ConsultaCep", url = "viacep.com.br/ws/01001000/json/")
public interface OpenFeignCep {

    @GetMapping
    EventResponseDto getCepInfo();
}
