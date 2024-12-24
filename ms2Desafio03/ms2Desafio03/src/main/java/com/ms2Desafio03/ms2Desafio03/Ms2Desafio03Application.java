package com.ms2Desafio03.ms2Desafio03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Ms2Desafio03Application {

	public static void main(String[] args) {
		SpringApplication.run(Ms2Desafio03Application.class, args);
	}

}
