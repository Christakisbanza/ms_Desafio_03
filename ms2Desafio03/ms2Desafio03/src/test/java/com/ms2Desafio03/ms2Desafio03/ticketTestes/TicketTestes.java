package com.ms2Desafio03.ms2Desafio03.ticketTestes;

import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketCreateDto;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketResponseDto;
import com.ms2Desafio03.ms2Desafio03.entity.model.HasTickets;
import com.ms2Desafio03.ms2Desafio03.exception.msg.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketTestes {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createTicket_ifTicketDataValid_ReturnTicketCreatedWithStatus201(){
        TicketResponseDto ticketResponseDto = testClient
                .post()
                .uri("tickets/v1/create-ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new TicketCreateDto("TestNameTicket", "45120321456", "TestNameTicket@gmail.com", "6776deec36c0ee22bd9e6397","B","R$50","R$10"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TicketResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(ticketResponseDto).isNotNull();
        Assertions.assertThat(ticketResponseDto.getId()).isNotNull();
        Assertions.assertThat(ticketResponseDto.getCustomerName()).isEqualTo("TestNameTicket");
        Assertions.assertThat(ticketResponseDto.getCustomerMail()).isEqualTo("TestNameTicket@gmail.com");
    }

    @Test
    public void createTicket_ifEventNameOrIdInvalid_ReturnErrorMessageWithStatus422(){
        ErrorMessage errorMessage = testClient
                .post()
                .uri("tickets/v1/create-ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new TicketCreateDto("TestNameTicket", "45120321456", "TestNameTicket@gmail.com", "6776deec36c0ee22bd9e6397","test","R$50","R$10"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(errorMessage).isNotNull();
        Assertions.assertThat(errorMessage.getStatus()).isEqualTo(422);

        testClient
                .post()
                .uri("tickets/v1/create-ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new TicketCreateDto("TestNameTicket", "45120321456", "TestNameTicket@gmail.com", "2","B","R$50","R$10"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(errorMessage).isNotNull();
        Assertions.assertThat(errorMessage.getStatus()).isEqualTo(422);
    }


    @Test
    public void findTicket_ifIdExist_ReturnTicketWithStatus200() {
        TicketResponseDto ticketResponseDto = testClient
                .get()
                .uri("tickets/v1/get-ticket/6771e1158e736f7124b65a0c")
                .exchange()
                .expectStatus().isOk()
                .expectBody(TicketResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(ticketResponseDto).isNotNull();
        Assertions.assertThat(ticketResponseDto.getId()).isEqualTo("6771e1158e736f7124b65a0c");
        Assertions.assertThat(ticketResponseDto.getCustomerName()).isEqualTo("Kilua");
    }

    @Test
    public void findTicket_ifIdDoNotExist_ReturnMsgErrorWithStatus404() {
        ErrorMessage errorMessage = testClient
                .get()
                .uri("tickets/v1/get-ticket/6776")
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(errorMessage).isNotNull();
        Assertions.assertThat(errorMessage.getStatus()).isEqualTo(404);
    }

    @Test
    public void ifEventHasTicket_Success_ReturnHasTicketWithStatus200() {
        HasTickets hasTickets = testClient
                .get()
                .uri("tickets/v1/check-tickets-by-event/6771d497a01a0b2ea320b011")
                .exchange()
                .expectStatus().isOk()
                .expectBody(HasTickets.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(hasTickets.getId()).isEqualTo("6771d497a01a0b2ea320b011");
    }

    @Test
    public void ifEventIdNotValid_Error_ReturnErrorMsgWithStatus404() {
        ErrorMessage errorMessage = testClient
                .get()
                .uri("tickets/v1/check-tickets-by-event/123")
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(errorMessage.getStatus()).isEqualTo(404);
    }

    @Test
    public void updateTicket_ifTicketDataValid_ReturnTicketUpdatedWithStatus200(){
        TicketResponseDto ticketResponseDto = testClient
                .put()
                .uri("tickets/v1/update-ticket/6771e1158e736f7124b65a0c")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new TicketCreateDto("Update", "12345678911", "update@gmail.com", "6776deec36c0ee22bd9e6397","B","R$50","R$10"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(TicketResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(ticketResponseDto).isNotNull();
        Assertions.assertThat(ticketResponseDto.getId()).isNotNull();
        Assertions.assertThat(ticketResponseDto.getCustomerName()).isEqualTo("Update");
        Assertions.assertThat(ticketResponseDto.getCustomerMail()).isEqualTo("update@gmail.com");
    }

    @Test
    public void updateTicket_ifTicketIdOrEventNameAndEventIdInvalid_ReturnErrorMessageWithStatus404(){
        ErrorMessage errorMessage = testClient
                .put()
                .uri("tickets/v1/update-ticket/6776e7d78510391d10239073")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new TicketCreateDto("Update", "12345678911", "update@gmail.com", "6776deec36c0ee22bd9e6397","B","R$50","R$10"))
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(errorMessage).isNotNull();
        Assertions.assertThat(errorMessage.getStatus()).isEqualTo(404);

        testClient
                .put()
                .uri("tickets/v1/update-ticket/6771e1158e736f7124b65a0c")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new TicketCreateDto("Update", "12345678911", "update@gmail.com", "5","B","R$50","R$10"))
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(errorMessage).isNotNull();
        Assertions.assertThat(errorMessage.getStatus()).isEqualTo(404);

        testClient
                .put()
                .uri("tickets/v1/update-ticket/6771e1158e736f7124b65a0c")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new TicketCreateDto("Update", "12345678911", "update@gmail.com", "6776deec36c0ee22bd9e6397","Z","R$50","R$10"))
                .exchange()
                .expectStatus().isEqualTo(404)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(errorMessage).isNotNull();
        Assertions.assertThat(errorMessage.getStatus()).isEqualTo(404);
    }

}
