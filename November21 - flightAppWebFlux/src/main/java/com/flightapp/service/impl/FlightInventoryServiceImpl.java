package com.flightapp.service.impl;

import com.flightapp.dto.InventoryRequest;
import com.flightapp.model.Airline;
import com.flightapp.model.FlightInventory;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.repository.FlightInventoryRepository;
import com.flightapp.service.FlightInventoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FlightInventoryServiceImpl implements FlightInventoryService {

    private final FlightInventoryRepository inventoryRepo;
    private final AirlineRepository airlineRepo;

    @Override
    public Mono<FlightInventory> addInventory(InventoryRequest req) {

        if (req == null) {
            return Mono.error(new IllegalArgumentException("InventoryRequest is null"));
        }
        if (req.getAirlineId() == null || req.getAirlineId().isBlank()) {
            return Mono.error(new IllegalArgumentException("airlineId is required"));
        }

        return airlineRepo.findByAirlineId(req.getAirlineId())
                .switchIfEmpty(Mono.error(new RuntimeException("Airline does not exist: " + req.getAirlineId())))
                .flatMap(airline -> {

                    FlightInventory inv = FlightInventory.builder()
                            .flightNumber(req.getFlightNumber())
                            .airlineId(req.getAirlineId())
                            .airlineName(airline.getAirlineName())
                            .origin(req.getOrigin())
                            .destination(req.getDestination())
                            .price(req.getPrice() == null ? 0.0 : req.getPrice())
                            .totalSeats(req.getTotalSeats() == null ? 0 : req.getTotalSeats())
                            .availableSeats(req.getTotalSeats() == null ? 0 : req.getTotalSeats())
                            .departureDate(req.getDepartureDate() != null ? req.getDepartureDate() : LocalDateTime.now().plusDays(1))
                            .build();

                    return inventoryRepo.save(inv);
                });
    }

    @Override
    public Flux<FlightInventory> searchFlights(String origin, String destination, String date) {
        return inventoryRepo.findByOriginAndDestination(origin, destination);
    }
}
