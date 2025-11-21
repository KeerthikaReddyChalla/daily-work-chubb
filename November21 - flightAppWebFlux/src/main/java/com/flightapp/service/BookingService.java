package com.flightapp.service;

import com.flightapp.dto.BookingRequest;
import com.flightapp.model.Booking;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingService {
    Mono<Booking> bookTicket(BookingRequest request);
    Mono<Booking> getByPnr(String pnr);
    Flux<Booking> getHistory(String email);
    Mono<String> cancel(String pnr);
}
