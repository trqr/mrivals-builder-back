package com.mrivals_builder.Mrivals_Builder.dtos.UserDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record NewPasswordRequestDTO(

        @NotBlank(message = "The password cannot be empty.")
        String oldPassword,

        @NotBlank(message = "Password cannot be empty.")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-])[A-Za-z\\d@$!%*?&._-]{8,}$",
                message = "Password must be at least 8 characters long, containing at least one uppercase letter, one lowercase letter, one number, and one special character."
        )
        String newPassword) {
}
