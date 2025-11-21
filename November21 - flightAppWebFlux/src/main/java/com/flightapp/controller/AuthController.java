package com.flightapp.controller;

import com.flightapp.dto.AuthResponse;
import com.flightapp.dto.LoginRequest;
import com.flightapp.dto.RegisterRequest;
import com.flightapp.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public Mono<AuthResponse> register(@Valid @RequestBody RegisterRequest req) {
        return authService.register(req);   
    }

    @PostMapping("/login")
    public Mono<AuthResponse> login(@Valid @RequestBody LoginRequest req) {
        return authService.login(req);     
    }
}
