package com.flightapp.controller;

import com.flightapp.dto.AirlineRequest;
import com.flightapp.model.Airline;
import com.flightapp.service.AirlineService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/v1.0/flight")
@RequiredArgsConstructor

public class AirlineController {

    private final AirlineService airlineService;

    @PostMapping("/airline")
    public Mono<Airline> addAirline(@Valid @RequestBody AirlineRequest request) {
        return airlineService.addAirline(request);
    }
}
