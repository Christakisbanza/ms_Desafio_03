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

import java.util.List;

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


    @Test
    public void findEvent_ifIdExist_ReturnEventWithStatus200() {
        EventResponseDto eventResponseDto = testClient
                .get()
                .uri("events/v1/get-event/6776deec36c0ee22bd9e6397")
                .exchange()
                .expectStatus().isOk()
                .expectBody(EventResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(eventResponseDto).isNotNull();
        Assertions.assertThat(eventResponseDto.getId()).isEqualTo("6776deec36c0ee22bd9e6397");
        Assertions.assertThat(eventResponseDto.getEventName()).isEqualTo("B");
    }

    @Test
    public void findEvent_ifIdDoNotExist_ReturnEventWithStatus404() {
        ErrorMessage errorMessage = testClient
                .get()
                .uri("events/v1/get-event/6776")
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(errorMessage).isNotNull();
        Assertions.assertThat(errorMessage.getStatus()).isEqualTo(404);
    }

    @Test
    public void findEvents_GetAll_ReturnEventsWithStatus200() {
        List<EventResponseDto> eventResponseDto = testClient
                .get()
                .uri("events/v1/get-all-events")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EventResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(eventResponseDto).isNotNull();
    }

    @Test
    public void findEvents_GetAllSorted_ReturnEventsWithStatus200() {
        List<EventResponseDto> eventResponseDto = testClient
                .get()
                .uri("events/v1/get-all-events/sorted")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EventResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(eventResponseDto).isNotNull();
    }

    @Test
    public void updateEvent_ifEventDataValid_ReturnEventUpdatedWithStatus200(){
        EventResponseDto eventResponseDto = testClient
                .put()
                .uri("events/v1/update-event/6776e7d78510391d10239073")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new EventCreateDto("TestNameUpdate", "2026/01/10 10:30", "80060050"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(EventResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(eventResponseDto).isNotNull();
        Assertions.assertThat(eventResponseDto.getId()).isNotNull();
        Assertions.assertThat(eventResponseDto.getEventName()).isEqualTo("TestNameUpdate");
        Assertions.assertThat(eventResponseDto.getCep()).isEqualTo("80060050");
    }

    @Test
    public void updateEvent_ifEventIdOrCepIsInvalid_ReturnErrorMessageWithStatus404(){
        ErrorMessage errorMessage = testClient
                .put()
                .uri("events/v1/update-event/6776e7d78510391d10239073")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new EventCreateDto("TestName", "2026/01/10 10:30", "810"))
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(errorMessage).isNotNull();
        Assertions.assertThat(errorMessage.getStatus()).isEqualTo(404);

        testClient
                .put()
                .uri("events/v1/update-event/6776e7d")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new EventCreateDto("TestName", "2026/01/10 10:30", "80060050"))
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(errorMessage).isNotNull();
        Assertions.assertThat(errorMessage.getStatus()).isEqualTo(404);
    }

    @Test
    public void deleteEvent_WithSuccess_ReturnStatus204(){
        testClient
                .delete()
                .uri("events/v1/delete-event/6776deec36c0ee22bd9e6397")
                .exchange()
                .expectStatus().isNoContent()
                .expectBody()
                .isEmpty();
    }

    @Test
    public void deleteEvent_WithError_ReturnStatus409(){
        ErrorMessage errorMessage = testClient
                .delete()
                .uri("events/v1/delete-event/6776deec")
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(errorMessage).isNotNull();
        Assertions.assertThat(errorMessage.getStatus()).isEqualTo(409);
    }

}
