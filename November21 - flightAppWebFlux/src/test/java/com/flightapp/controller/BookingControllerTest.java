package com.flightapp.controller;

import com.flightapp.config.JwtAuthFilter;
import com.flightapp.config.JwtUtil;
import com.flightapp.dto.BookingRequest;
import com.flightapp.model.Booking;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = BookingController.class)
@AutoConfigureMockMvc(addFilters = false)
@ImportAutoConfiguration(exclude = { 
	    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
	    org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
	})

 class BookingControllerTest {
	@MockBean
    JwtAuthFilter jwtAuthFilter;

    @MockBean
    JwtUtil jwtUtil;
    
    @MockBean
    private com.flightapp.service.BookingService bookingService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testBookTicket() throws Exception {
        BookingRequest req = new BookingRequest();
        req.setSeats(2);

        Booking saved = new Booking();
        saved.setPnr("PNR999");

        Mockito.when(bookingService.bookTicket(any())).thenReturn(saved);

        mockMvc.perform(post("/api/v1.0/flight/booking/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pnr").value("PNR999"));
    }

    @Test
    void testGetByPnr() throws Exception {
        Booking b = new Booking();
        b.setPnr("PNR123");

        Mockito.when(bookingService.getByPnr("PNR123")).thenReturn(b);

        mockMvc.perform(get("/api/v1.0/flight/booking/ticket/PNR123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pnr").value("PNR123"));
    }

    @Test
    void testCancelBooking() throws Exception {
        Mockito.when(bookingService.cancel("PNR777")).thenReturn("Cancelled Successfully");

        mockMvc.perform(delete("/api/v1.0/flight/booking/cancel/PNR777"))
                .andExpect(status().isOk());
    }
}
