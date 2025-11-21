package com.flightapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {
    @NotBlank
    private String flightNumber;
    @NotBlank
    private String origin;
    @NotBlank
    private String destination;
    @NotNull
    private Double price;
    @NotNull
    private Integer totalSeats;
    @NotBlank
    private String airlineId;
    private LocalDateTime departureDate;
}
