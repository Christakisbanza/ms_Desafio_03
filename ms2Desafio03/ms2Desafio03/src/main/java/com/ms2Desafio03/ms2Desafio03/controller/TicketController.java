package com.ms2Desafio03.ms2Desafio03.controller;

import com.ms2Desafio03.ms2Desafio03.entity.model.HasTickets;
import com.ms2Desafio03.ms2Desafio03.entity.Ticket;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketCreateDto;
import com.ms2Desafio03.ms2Desafio03.entity.dto.TicketResponseDto;
import com.ms2Desafio03.ms2Desafio03.exception.msg.ErrorMessage;
import com.ms2Desafio03.ms2Desafio03.mapper.TicketMapper;
import com.ms2Desafio03.ms2Desafio03.producer.MsgEmailProducer;
import com.ms2Desafio03.ms2Desafio03.service.TicketServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tickets/v1")
public class TicketController {

    @Autowired
    private TicketServices ticketServices;

    @Autowired
    private MsgEmailProducer msgEmailProducer;


    @Operation(
            summary = "Create a new Ticket for a Event",
            description = "Endpoint to create a new Ticket for a Event",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Ticket successfully created",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TicketResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Invalid data for creating the Ticket",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PostMapping("/create-ticket")
    public ResponseEntity<TicketResponseDto> save(@RequestBody TicketCreateDto ticketCreatDto){
        Ticket ticket = ticketServices.save(TicketMapper.toTicket(ticketCreatDto));
        msgEmailProducer.sendEmailMsg(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(TicketMapper.toDto(ticket));
    }


    @Operation(
            summary = "Get Ticket by id",
            description = "Endpoint to get Ticket by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ticket successfully retrieved",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TicketResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ticket not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @GetMapping("/get-ticket/{id}")
    public ResponseEntity<TicketResponseDto> getById(@PathVariable String id){
        Ticket ticket = ticketServices.getById(id);
        return ResponseEntity.ok().body(TicketMapper.toDto(ticket));
    }

    @Operation(
            summary = "Get all Ticket",
            description = "Endpoint to get all Ticket",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Tickets successfully retrieved",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TicketResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Tickets not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @GetMapping("/get-all-tickets")
    public ResponseEntity<List<TicketResponseDto>> getAll(){
        List<Ticket> list = ticketServices.getAll();
        return ResponseEntity.ok().body(TicketMapper.toDtos(list));
    }


    @Operation(
            summary = "Check if events has tickets sells",
            description = "Endpoint to check if events has tickets sells",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "True or False",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TicketResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Event not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @GetMapping("/check-tickets-by-event/{eventId}")
    public ResponseEntity<HasTickets> hasTickets(@PathVariable String eventId ){
        HasTickets hasTickets = ticketServices.hasTickets(eventId);
        return ResponseEntity.ok().body(hasTickets);
    }


    @Operation(
            summary = "Update a Ticket",
            description = "Endpoint to update a Ticket",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ticket successfully updated",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TicketResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ticket or Event not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PutMapping("/update-ticket/{id}")
    public ResponseEntity<TicketResponseDto> upDateById(@PathVariable String id, @RequestBody TicketCreateDto ticketCreatDto){
        Ticket ticket = ticketServices.upDateById(id, ticketCreatDto);
        return ResponseEntity.ok().body(TicketMapper.toDto(ticket));
    }


    @Operation(
            summary = "Delete a Ticket",
            description = "Endpoint to delete a Ticket",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Ticket successfully deleted"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ticket not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @DeleteMapping("/cancel-ticket/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        ticketServices.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
