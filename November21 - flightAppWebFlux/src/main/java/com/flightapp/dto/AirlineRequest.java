package com.flightapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AirlineRequest {

    @NotBlank
    private String airlineId;

    @NotBlank
    private String airlineName;

    private String airlineLogoUrl;
}
