package com.flightapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class BookingRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String flightNumber;

    @NotNull
    private Integer seats;

    private String mealType;

    private List<PassengerRequest> passengers;
}
