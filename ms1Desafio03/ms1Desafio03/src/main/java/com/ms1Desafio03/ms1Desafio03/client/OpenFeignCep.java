package com.ms1Desafio03.ms1Desafio03.client;

import com.ms1Desafio03.ms1Desafio03.entity.dto.EventResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ConsultaCep", url = "viacep.com.br/ws")
public interface OpenFeignCep {

    @GetMapping("/{cep}/json/")
    EventResponseDto getCepInfo(@PathVariable String cep);
}
