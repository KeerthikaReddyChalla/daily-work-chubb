package com.flightapp.controller;

import com.flightapp.dto.BookingRequest;
import com.flightapp.model.Booking;
import com.flightapp.service.BookingService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/flight/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/book")
    public Mono<Booking> book(@RequestBody BookingRequest req) {
        return bookingService.bookTicket(req);
    }

    @GetMapping("/ticket/{pnr}")
    public Mono<Booking> get(@PathVariable String pnr) {
        return bookingService.getByPnr(pnr);
    }

    @GetMapping("/history/{email}")
    public Flux<Booking> history(@PathVariable String email) {
        return bookingService.getHistory(email);
    }

    @DeleteMapping("/cancel/{pnr}")
    public Mono<String> cancel(@PathVariable String pnr) {
        return bookingService.cancel(pnr);
    }
}
