package com.flightapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PassengerRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String gender;

    @NotNull
    private Integer age;
}
