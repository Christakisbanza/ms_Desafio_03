package com.ms2Desafio03.ms2Desafio03.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "consultaMs1", url = "http://localhost:8080/events/v1")
public interface OpenFeignMs1 {

}
