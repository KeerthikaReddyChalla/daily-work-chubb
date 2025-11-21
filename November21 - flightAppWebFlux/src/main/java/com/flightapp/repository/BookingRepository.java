package com.flightapp.repository;

import com.flightapp.model.Booking;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;

public interface BookingRepository extends ReactiveCrudRepository<Booking, Long> {
    Mono<Booking> findByPnr(String pnr);
    Flux<Booking> findByEmail(String email);
}
