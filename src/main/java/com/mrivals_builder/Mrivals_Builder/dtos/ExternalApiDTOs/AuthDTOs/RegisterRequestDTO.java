package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.AuthDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

    @NotBlank(message = "The username cannot be empty.")
    private String username;

    @NotBlank(message = "Email cannot be empty.")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Invalid email format."
    )
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,}$",
            message = "Password must be at least 8 characters long, containing at least one uppercase letter, one lowercase letter, one number, and one special character."
    )
    private String password;
}
