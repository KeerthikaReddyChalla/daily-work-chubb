package com.flightapp.service.impl;

import com.flightapp.dto.AirlineRequest;
import com.flightapp.model.Airline;
import com.flightapp.repository.AirlineRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AirlineServiceImplTest {

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AirlineServiceImpl airlineService;

    @Test
    void testAddAirline() {
        AirlineRequest req = new AirlineRequest();
        req.setAirlineId("AI123");
        req.setAirlineName("Air India");
        req.setAirlineLogoUrl("logo.png");

        Airline saved = Airline.builder()
                .id(1L)
                .airlineId("AI123")
                .airlineName("Air India")
                .airlineLogoUrl("logo.png")
                .build();

        when(airlineRepository.save(any(Airline.class))).thenReturn(saved);

        Airline response = airlineService.addAirline(req);

        assertNotNull(response);
        assertEquals("AI123", response.getAirlineId());
        assertEquals("Air India", response.getAirlineName());
    }
}
