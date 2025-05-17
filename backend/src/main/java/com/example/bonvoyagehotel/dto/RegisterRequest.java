package com.example.bonvoyagehotel.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    @Size(min=4, max=20)
    private String username;

    @NotBlank
    @Size(min=6, max=100)
    private String password;

    @NotBlank
    @Email
    private String email;
}
