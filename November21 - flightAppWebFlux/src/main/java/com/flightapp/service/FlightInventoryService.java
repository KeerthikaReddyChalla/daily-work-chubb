package com.flightapp.service;

import java.util.*;
import com.flightapp.dto.InventoryRequest;
import com.flightapp.model.FlightInventory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FlightInventoryService {
	Mono<FlightInventory> addInventory(InventoryRequest request);
    Flux<FlightInventory> searchFlights(String origin, String destination, String date);
}


