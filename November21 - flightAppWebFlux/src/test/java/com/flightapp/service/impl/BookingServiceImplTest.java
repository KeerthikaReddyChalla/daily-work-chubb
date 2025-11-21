package com.flightapp.service.impl;

import com.flightapp.dto.BookingRequest;
import com.flightapp.dto.PassengerRequest;
import com.flightapp.model.Booking;
import com.flightapp.model.FlightInventory;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.FlightInventoryRepository;
import com.flightapp.service.BookingService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

 class BookingServiceImplTest {

    private final BookingRepository bookingRepo = Mockito.mock(BookingRepository.class);
    private final FlightInventoryRepository invRepo = Mockito.mock(FlightInventoryRepository.class);

    private final BookingService bookingService =
            new BookingServiceImpl(bookingRepo, invRepo);

    @Test
    void testBookTicket() {

        FlightInventory inv = new FlightInventory();
        inv.setAvailableSeats(10);
        inv.setPrice(1000.0);

        BookingRequest req = new BookingRequest();
        req.setSeats(2);
        req.setFlightNumber("FL123");
        req.setEmail("testing@mail.com");
        req.setMealType("VEG");

        PassengerRequest p = new PassengerRequest();
        p.setName("Test");
        p.setGender("F");
        p.setAge(22);

        req.setPassengers(List.of(p));
        Mockito.when(invRepo.findByFlightNumber("FL123")).thenReturn(inv);
        Mockito.when(bookingRepo.save(any())).thenAnswer(i -> i.getArgument(0));

        Booking result = bookingService.bookTicket(req);

        assertNotNull(result.getPnr());
        assertEquals("test@mail.com", result.getEmail());
        assertEquals(2, result.getSeats());
        assertEquals(2000.0, result.getTotalAmount());
    }
}
