package com.flightapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Booking {


    private Long id;

    private String pnr;

    private String email;

   
    @JsonIgnoreProperties({"bookings"})  
    private FlightInventory flightInventory;

    private int seats;

    private String mealType;

    private Double totalAmount;

    private LocalDateTime bookingDate;

    private boolean isCancelled;

    @JsonManagedReference
    private List<Passenger> passengers;
}
