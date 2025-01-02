package com.ms1Desafio03.ms1Desafio03.controller;

import com.ms1Desafio03.ms1Desafio03.entity.Event;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventCreateDto;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventResponseDto;
import com.ms1Desafio03.ms1Desafio03.entity.dto.EventUpdateDto;
import com.ms1Desafio03.ms1Desafio03.exception.msg.ErrorMessage;
import com.ms1Desafio03.ms1Desafio03.mapper.EventMapper;
import com.ms1Desafio03.ms1Desafio03.service.EventService;
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
@RequestMapping("events/v1")
public class EventController {

    @Autowired
    private EventService eventService;

    @Operation(
            summary = "Create a new Event",
            description = "Endpoint to create a new Event",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Event successfully created",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Invalid data for creating the Event",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PostMapping("/create-event")
    public ResponseEntity<EventResponseDto> save(@RequestBody EventCreateDto eventCreateDto){
        Event event = eventService.save(EventMapper.toEvent(eventCreateDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(EventMapper.toDto(event));
    }


    @Operation(
            summary = "Get Event by id",
            description = "Endpoint to get Event by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Event successfully retrieved",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Event not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @GetMapping("/get-event/{id}")
    public ResponseEntity<EventResponseDto> getById(@PathVariable String id){
        Event event = eventService.getById(id);
        return ResponseEntity.ok().body(EventMapper.toDto(event));
    }


    @Operation(
            summary = "Get all Event",
            description = "Endpoint to get all Event",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Events successfully retrieved",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDto.class))
                    )
            }
    )
    @GetMapping("/get-all-events")
    public ResponseEntity<List<EventResponseDto>> getAll(){
        List<Event> list = eventService.getAll();
        return ResponseEntity.ok().body(EventMapper.toDtos(list));
    }


    @Operation(
            summary = "Get all Event in alphabetical order",
            description = "Endpoint to get all Event in alphabetical order",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Events successfully retrieved",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDto.class))
                    )
            }
    )
    @GetMapping("/get-all-events/sorted")
    public ResponseEntity<List<EventResponseDto>> getAllAsc(){
        List<Event> list = eventService.getAllAsc();
        return ResponseEntity.ok().body(EventMapper.toDtos(list));
    }


    @Operation(
            summary = "Update a Event",
            description = "Endpoint to update a Event",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Event successfully updated",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Ticket or Event not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @PutMapping("/update-event/{id}")
    public ResponseEntity<EventResponseDto> upDateById(@PathVariable String id, @RequestBody EventUpdateDto eventUpdateDto){
        Event event = eventService.upDateById(id, EventMapper.toEvent(eventUpdateDto));
        return ResponseEntity.ok().body(EventMapper.toDto(event));
    }


    @Operation(
            summary = "Delete a Event",
            description = "Endpoint to delete a Event",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Event successfully deleted"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflito",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
                    )
            }
    )
    @DeleteMapping("/delete-event/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        eventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
