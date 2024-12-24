package com.ms1Desafio03.ms1Desafio03.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ConsultaCep", url = "viacep.com.br/ws/01001000/json/")
public class OpenFeignCep {

}
