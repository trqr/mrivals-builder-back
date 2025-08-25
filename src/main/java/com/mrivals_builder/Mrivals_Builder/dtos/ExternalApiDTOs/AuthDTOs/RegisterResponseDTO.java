package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.AuthDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
    private String token;
    private UserDTO user;
}
