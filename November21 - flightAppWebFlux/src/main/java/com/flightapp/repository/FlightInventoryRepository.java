package com.flightapp.repository;

import com.flightapp.model.FlightInventory;

import reactor.core.publisher.*;

import java.util.*;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FlightInventoryRepository extends ReactiveCrudRepository<FlightInventory, Long> {
    Mono<FlightInventory> findByFlightNumber(String flightNumber);
    Flux<FlightInventory> findByOriginAndDestination(String origin, String destination);

}

