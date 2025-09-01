package com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequestDTO(
        @NotEmpty(message = "Email cannot be empty.")
        String email,

        @NotEmpty(message = "Password cannot be empty.")
        String password
) {}
