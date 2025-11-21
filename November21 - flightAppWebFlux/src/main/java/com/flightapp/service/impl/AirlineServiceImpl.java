package com.flightapp.service.impl;
import reactor.core.publisher.Mono;

import com.flightapp.dto.AirlineRequest;
import com.flightapp.model.Airline;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.service.AirlineService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Override
    public Mono<Airline> addAirline(AirlineRequest request) {

        Airline airline = Airline.builder()
                .airlineId(request.getAirlineId())
                .airlineName(request.getAirlineName())
                .airlineLogoUrl(request.getAirlineLogoUrl())
                .build();

        return airlineRepository.save(airline);
    }
}
