package com.flightapp.repository;

import com.flightapp.model.Airline;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ImportAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
})
class AirlineRepositoryTest {

    @Autowired
    private AirlineRepository airlineRepository;
    
    @MockBean
    private com.flightapp.config.JwtAuthFilter jwtAuthFilter;

    @MockBean
    private com.flightapp.config.JwtUtil jwtUtil;

    @Test
    void testSaveAndFindByAirlineId() {
        Airline airline = Airline.builder()
                .airlineId("AI123")
                .airlineName("Air India")
                .airlineLogoUrl("logo.png")
                .build();

        airlineRepository.save(airline);

        Airline result = airlineRepository.findByAirlineId("AI123");

        assertNotNull(result);
        assertEquals("Air India", result.getAirlineName());
    }
}
