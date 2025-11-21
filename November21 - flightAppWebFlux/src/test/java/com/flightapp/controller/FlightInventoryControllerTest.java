package com.flightapp.controller;

import com.flightapp.dto.InventoryRequest;
import com.flightapp.model.FlightInventory;
import com.flightapp.service.FlightInventoryService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = FlightInventoryController.class)
 class FlightInventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightInventoryService inventoryService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testAddInventory() throws Exception {

        InventoryRequest req = new InventoryRequest();
        req.setFlightNumber("FL123");

        FlightInventory saved = new FlightInventory();
        saved.setId(1L);
        saved.setFlightNumber("FL123");

        Mockito.when(inventoryService.addInventory(any())).thenReturn(saved);

        mockMvc.perform(post("/api/v1.0/flight/inventory/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightNumber").value("FL123"));
    }
}
