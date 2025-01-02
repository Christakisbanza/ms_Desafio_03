package com.ms1Desafio03.ms1Desafio03.eventTestes;

import com.ms1Desafio03.ms1Desafio03.entity.Event;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventCreateDto;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventResponseDto;
import com.ms1Desafio03.ms1Desafio03.exception.msg.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventTestes {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createEvent_ifEventDataValid_ReturnEventCreatedWithStatus201(){
        EventResponseDto eventResponseDto = testClient
                .post()
                .uri("events/v1/create-event")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new EventCreateDto("TestName", "2026/01/10 10:30", "81020050"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EventResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(eventResponseDto).isNotNull();
        Assertions.assertThat(eventResponseDto.getId()).isNotNull();
        Assertions.assertThat(eventResponseDto.getEventName()).isEqualTo("TestName");
        Assertions.assertThat(eventResponseDto.getCep()).isEqualTo("81020050");
    }

    @Test
    public void createEvent_ifEventCepIsInvalid_ReturnErrorMessageWithStatus422(){
        ErrorMessage errorMessage = testClient
                .post()
                .uri("events/v1/create-event")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new EventCreateDto("TestName", "2026/01/10 10:30", "81020"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(errorMessage).isNotNull();
        Assertions.assertThat(errorMessage.getStatus()).isEqualTo(422);
    }

}
