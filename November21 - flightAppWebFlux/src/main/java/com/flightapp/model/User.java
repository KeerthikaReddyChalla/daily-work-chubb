package com.flightapp.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

   
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Enter valid email")
   
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    
    
    private Set<Role> roles;
}
