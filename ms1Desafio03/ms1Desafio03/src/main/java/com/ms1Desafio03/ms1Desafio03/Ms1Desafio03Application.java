package com.ms1Desafio03.ms1Desafio03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Ms1Desafio03Application {

	public static void main(String[] args) {
		SpringApplication.run(Ms1Desafio03Application.class, args);
	}

}
