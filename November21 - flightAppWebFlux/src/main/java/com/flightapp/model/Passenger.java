package com.flightapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Passenger {

   
    private Long id;
    
    
    @JsonBackReference
    private Booking booking;

    private String name;

    private String gender;

    private int age;
}
