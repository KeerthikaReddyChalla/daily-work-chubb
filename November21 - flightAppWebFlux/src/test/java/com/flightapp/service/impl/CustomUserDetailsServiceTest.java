package com.flightapp.service.impl;

import com.flightapp.model.Role;
import com.flightapp.model.User;
import com.flightapp.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService service;

    @Test
    void testLoadUserByUsername() {

        Set<Role> roles = new HashSet<>();
        Role r = new Role();
        r.setId(1L);
        r.setName("USER");
        roles.add(r);

        User u = new User();
        u.setId(1L);
        u.setName("Keerthi");
        u.setEmail("keerthi@gmail.com");
        u.setPassword("pass");
        u.setRoles(roles);

        when(userRepository.findByEmail("keerthi@gmail.com"))
                .thenReturn(Optional.of(u));

        var result = service.loadUserByUsername("keerthi@gmail.com");
        assertEquals("keerthi@gmail.com", result.getUsername());
        assertEquals("pass", result.getPassword());
        assertFalse(result.getAuthorities().isEmpty());
    }
}
