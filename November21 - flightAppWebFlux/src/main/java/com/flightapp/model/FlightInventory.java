package com.flightapp.model;


import java.util.*;
import lombok.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FlightInventory {

   
    private Long id;

    private String flightNumber;
    
    private String airlineId;
    
    private String airlineName;

    private String origin;

    private String destination;

    private Double price;

    private Integer totalSeats;

    private Integer availableSeats;

    private LocalDateTime departureDate;
    
    @JsonIgnore
    private List<Booking> bookings;

}
