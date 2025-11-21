package com.flightapp.service.impl;

import com.flightapp.config.JwtUtil;
import com.flightapp.dto.AuthResponse;
import com.flightapp.dto.LoginRequest;
import com.flightapp.dto.RegisterRequest;
import com.flightapp.model.Role;
import com.flightapp.model.User;
import com.flightapp.repository.RoleRepository;
import com.flightapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements com.flightapp.service.AuthService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public Mono<AuthResponse> register(RegisterRequest req) {

        return userRepo.findByEmail(req.getEmail())
                .flatMap(existing -> Mono.error(new DuplicateKeyException(
                        "User already exists with email: " + req.getEmail()
                )))
                .switchIfEmpty(
                        roleRepo.findByName("ROLE_USER")
                                .switchIfEmpty(roleRepo.save(
                                        Role.builder().name("ROLE_USER").build()
                                ))
                                .flatMap(role -> {

                                    User user = User.builder()
                                            .name(req.getName())
                                            .email(req.getEmail())
                                            .password(encoder.encode(req.getPassword()))
                                            .roles(Set.of(role))
                                            .build();

                                    return userRepo.save(user)
                                            .map(saved -> new AuthResponse(
                                                    jwtUtil.generateToken(saved.getEmail()),
                                                    saved.getEmail(),
                                                    "Registered Successfully"
                                            ));
                                })
                );
    }

    @Override
    public Mono<AuthResponse> login(LoginRequest req) {

        return userRepo.findByEmail(req.getEmail())
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
                .flatMap(user -> {

                    if (!encoder.matches(req.getPassword(), user.getPassword())) {
                        return Mono.error(new BadCredentialsException("Invalid email or password"));
                    }

                    return Mono.just(
                            new AuthResponse(
                                    jwtUtil.generateToken(user.getEmail()),
                                    user.getEmail(),
                                    "Login Successful"
                            )
                    );
                });
    }
}
