package com.example.bonvoyagehotel.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoomRequest {

    @NotBlank(message = "Room number must not be blank")
    @Size(max = 10, message = "Room number can't exceed 10 characters")
    private String roomNumber;

    @NotBlank(message = "Room type must not be blank")
    private String type;

    @NotNull(message = "Price per night is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price per night must be non-negative")
    private Double pricePerNight;

    private String description;

    private boolean available = true;
}
