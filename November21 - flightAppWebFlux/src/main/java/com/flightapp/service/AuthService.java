package com.flightapp.service;

import com.flightapp.dto.LoginRequest;
import com.flightapp.dto.RegisterRequest;
import com.flightapp.dto.AuthResponse;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<AuthResponse> login(LoginRequest request);

    Mono<AuthResponse> register(RegisterRequest request);
}
