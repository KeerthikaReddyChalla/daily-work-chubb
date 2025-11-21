package com.flightapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightapp.dto.AirlineRequest;
import com.flightapp.model.Airline;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(controllers = AirlineController.class)
@AutoConfigureMockMvc(addFilters = false)
@ImportAutoConfiguration(exclude = { 
	    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
	    org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
	})

class AirlineControllerTest {
	
	@MockBean
    private com.flightapp.service.AirlineService airlineService;

    @MockBean               
    private com.flightapp.config.JwtAuthFilter jwtAuthFilter;

    @MockBean              
    private com.flightapp.config.JwtUtil jwtUtil;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddAirline() throws Exception {

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

        Mockito.when(airlineService.addAirline(any(AirlineRequest.class)))
                .thenReturn(saved);

        mockMvc.perform(post("/api/v1.0/flight/airline")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.airlineId").value("AI123"))
                .andExpect(jsonPath("$.airlineName").value("Air India"));
    }
}
