package com.flightapp.service.impl;

import com.flightapp.dto.InventoryRequest;
import com.flightapp.model.Airline;
import com.flightapp.model.FlightInventory;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.repository.FlightInventoryRepository;
import com.flightapp.service.FlightInventoryService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

 class FlightInventoryServiceImplTest {

    private final FlightInventoryRepository invRepo = Mockito.mock(FlightInventoryRepository.class);
    private final AirlineRepository airlineRepo = Mockito.mock(AirlineRepository.class);

    private final FlightInventoryService service =
            new FlightInventoryServiceImpl(invRepo, airlineRepo);

    @Test
    void testAddInventory() {

        InventoryRequest req = new InventoryRequest();
        req.setFlightNumber("FL123");
        req.setAirlineId("AI001");
        req.setOrigin("DEL");
        req.setDestination("HYD");
        req.setPrice(5000.0);
        req.setTotalSeats(150);
        req.setDepartureDate(LocalDateTime.now().plusDays(1));


        Airline airline = new Airline();
        airline.setAirlineId("AI001");
        airline.setAirlineName("Air India");
        airline.setAirlineLogoUrl("logo.png");

        Mockito.when(airlineRepo.findByAirlineId("AI001"))
               .thenReturn(airline);

        FlightInventory saved = new FlightInventory();
        saved.setFlightNumber("FL123");

        Mockito.when(invRepo.save(any())).thenReturn(saved);

        FlightInventory result = service.addInventory(req);

        assertNotNull(result);
        assertEquals("FL123", result.getFlightNumber());
    }
}
