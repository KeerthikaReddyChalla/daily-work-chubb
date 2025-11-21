package com.flightapp.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airline {

   
    private Long id;

    @NotBlank(message = "Airline ID cannot be empty")
   
    private String airlineId;

    @NotBlank(message = "Airline name cannot be empty")
    private String airlineName;

    private String airlineLogoUrl; 
}
