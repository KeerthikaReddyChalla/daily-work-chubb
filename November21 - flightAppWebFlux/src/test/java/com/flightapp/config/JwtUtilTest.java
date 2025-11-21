package com.flightapp.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private final JwtUtil jwtUtil = new JwtUtil();

    @Test
    void testTokenGenerateAndValidate() {
        String token = jwtUtil.generateToken("keerthi@example.com");
        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token));
    }

    @Test
    void testExtractUsername() {
        String token = jwtUtil.generateToken("test@mail.com");
        assertEquals("test@mail.com", jwtUtil.extractUsername(token));
    }
}
