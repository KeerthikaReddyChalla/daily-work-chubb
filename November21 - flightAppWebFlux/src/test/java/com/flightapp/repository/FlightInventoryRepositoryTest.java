package com.flightapp.repository;

import com.flightapp.model.FlightInventory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create",
        "spring.jpa.show-sql=false"
})
class FlightInventoryRepositoryTest {

    @Autowired
    private FlightInventoryRepository inventoryRepository;

    @Test
    void testSaveAndFindByFlightNumber() {
        FlightInventory inv = FlightInventory.builder()
                .flightNumber("FL100")
                .origin("HYD")
                .destination("DEL")
                .price(5000.0)
                .totalSeats(150)
                .availableSeats(150)
                .departureDate(LocalDateTime.now().plusDays(1))
                .build();

        inventoryRepository.save(inv);

        FlightInventory result = inventoryRepository.findByFlightNumber("FL100");

        assertNotNull(result);
        assertEquals("HYD", result.getOrigin());
        assertEquals(150, result.getTotalSeats());
    }
}
