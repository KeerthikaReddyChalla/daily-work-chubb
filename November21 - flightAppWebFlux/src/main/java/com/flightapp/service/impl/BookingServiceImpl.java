package com.flightapp.service.impl;

import com.flightapp.dto.BookingRequest;
import com.flightapp.model.Booking;
import com.flightapp.model.FlightInventory;
import com.flightapp.model.Passenger;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightInventoryRepository;
import com.flightapp.service.BookingService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepo;
    private final FlightInventoryRepository inventoryRepo;

    @Override
    public Mono<Booking> bookTicket(BookingRequest req) {

        return inventoryRepo.findByFlightNumber(req.getFlightNumber())
                .switchIfEmpty(Mono.error(new RuntimeException("Flight not found: " + req.getFlightNumber())))
                .flatMap(inv -> {

                    if (inv.getAvailableSeats() < req.getSeats()) {
                        return Mono.error(new IllegalStateException("Not enough seats available"));
                    }

                    // update seats
                    inv.setAvailableSeats(inv.getAvailableSeats() - req.getSeats());

                    return inventoryRepo.save(inv)
                            .flatMap(updatedInv -> {

                                List<Passenger> passengers = req.getPassengers()
                                        .stream()
                                        .map(p -> Passenger.builder()
                                                .name(p.getName())
                                                .gender(p.getGender())
                                                .age(p.getAge())
                                                .build())
                                        .toList();

                                Booking booking = Booking.builder()
                                        .pnr(UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                                        .email(req.getEmail())
                                        .flightInventory(updatedInv)
                                        .seats(req.getSeats())
                                        .mealType(req.getMealType())
                                        .passengers(passengers)
                                        .totalAmount(updatedInv.getPrice() * req.getSeats())
                                        .bookingDate(LocalDateTime.now())
                                        .isCancelled(false)
                                        .build();

                                return bookingRepo.save(booking);
                            });
                });
    }

    @Override
    public Mono<Booking> getByPnr(String pnr) {
        return bookingRepo.findByPnr(pnr)
                .switchIfEmpty(Mono.error(new RuntimeException("PNR not found: " + pnr)));
    }

    @Override
    public Flux<Booking> getHistory(String email) {
        return bookingRepo.findByEmail(email);
    }

    @Override
    public Mono<String> cancel(String pnr) {

        return bookingRepo.findByPnr(pnr)
                .switchIfEmpty(Mono.error(new RuntimeException("Invalid PNR")))
                .flatMap(booking -> {

                    FlightInventory inv = booking.getFlightInventory();
                    inv.setAvailableSeats(inv.getAvailableSeats() + booking.getSeats());

                    return inventoryRepo.save(inv)
                            .then(Mono.defer(() -> {
                                booking.setCancelled(true);
                                return bookingRepo.save(booking)
                                        .thenReturn("Cancelled Successfully");
                            }));
                });
    }
}
