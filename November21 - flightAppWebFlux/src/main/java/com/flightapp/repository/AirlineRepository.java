package com.flightapp.repository;

import com.flightapp.model.Airline;

import reactor.core.publisher.Mono;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends ReactiveCrudRepository<Airline, Long> {

    Mono<Airline> findByAirlineId(String airlineId);
    
}
