package com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotEmpty(message = "Email cannot be empty.")
    private String email;
    @NotEmpty(message = "Password cannot be empty.")
    private String password;

}
