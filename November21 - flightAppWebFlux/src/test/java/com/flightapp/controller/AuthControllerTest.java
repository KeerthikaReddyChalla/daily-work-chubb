package com.flightapp.controller;

import com.flightapp.config.JwtAuthFilter;
import com.flightapp.config.JwtUtil;
import com.flightapp.dto.AuthResponse;
import com.flightapp.dto.LoginRequest;
import com.flightapp.dto.RegisterRequest;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ImportAutoConfiguration(exclude = { 
	    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
	    org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
	})

class AuthControllerTest {
	
	@MockBean
    private com.flightapp.service.AuthService authService;

    @MockBean
    private JwtAuthFilter jwtAuthFilter;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testLogin() throws Exception {
        LoginRequest req = new LoginRequest("test@gmail.com", "password");
        AuthResponse res = new AuthResponse("TOKEN123");

        Mockito.when(authService.login(any())).thenReturn(res);

        mockMvc.perform(post("/api/v1.0/flight/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("TOKEN123"));
    }

    @Test
    void testRegister() throws Exception {
        RegisterRequest req = new RegisterRequest("john", "john@mail.com", "1234");
        AuthResponse res = new AuthResponse("NEWTOKEN");

        Mockito.when(authService.register(any())).thenReturn(res);

        mockMvc.perform(post("/api/v1.0/flight/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("NEWTOKEN"));
    }
}
