package com.flightapp.repository;

import com.flightapp.model.Airline;
import com.flightapp.model.Booking;
import com.flightapp.model.FlightInventory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ImportAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
})
class BookingRepositoryTest {

    @MockBean
    private com.flightapp.config.JwtAuthFilter jwtAuthFilter;

    @MockBean
    private com.flightapp.config.JwtUtil jwtUtil;


    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightInventoryRepository inventoryRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Test
    void testSaveAndFindByPnr() {

        Airline airline = Airline.builder()
                .airlineId("AI123")
                .airlineName("Air India")
                .airlineLogoUrl("logo.png")
                .build();
        airlineRepository.save(airline);
        FlightInventory inv = FlightInventory.builder()
                .flightNumber("AI-202")
                .airlineId(airline.getAirlineId())        
                .airlineName(airline.getAirlineName())
                .origin("HYD")
                .destination("DEL")
                .departureDate(LocalDateTime.now().plusDays(1)) 
                .totalSeats(180)
                .availableSeats(180)
                .price(4500.0)
                .build();
        inventoryRepository.save(inv);
        Booking booking = Booking.builder()
                .pnr("PNR12345")
                .email("keerthi@gmail.com")
                .flightInventory(inv)
                .seats(2)                       
                .totalAmount(9000.0)
                .bookingDate(LocalDateTime.now())
                .build();
        bookingRepository.save(booking);
        Booking result = bookingRepository.findByPnr("PNR12345");

        assertNotNull(result);
        assertEquals("keerthi@gmail.com", result.getEmail());
        assertEquals("AI-202", result.getFlightInventory().getFlightNumber());
        assertEquals("Air India", result.getFlightInventory().getAirlineName());
        assertEquals(2, result.getSeats());
    }
}
