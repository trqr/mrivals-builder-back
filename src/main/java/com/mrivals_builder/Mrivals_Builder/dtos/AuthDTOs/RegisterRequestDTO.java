package com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterRequestDTO(

        @NotBlank(message = "The username cannot be empty.")
        String username,

        @NotBlank(message = "Email cannot be empty.")
        @Pattern(
                regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
                message = "Invalid email format."
        )
        String email,

        @NotBlank(message = "Password cannot be empty.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{12,}$",
                message = "Password must be at least 12 characters long, containing at least one uppercase letter, one lowercase letter, one number, and one special character."
        )
        String password
) {}
