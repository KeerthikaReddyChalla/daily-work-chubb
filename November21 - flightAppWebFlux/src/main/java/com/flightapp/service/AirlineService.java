package com.flightapp.service;

import com.flightapp.dto.AirlineRequest;
import com.flightapp.model.Airline;

import reactor.core.publisher.Mono;


public interface AirlineService {
    Mono<Airline> addAirline(AirlineRequest request);

	
}
