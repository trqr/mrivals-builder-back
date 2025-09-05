package com.mrivals_builder.Mrivals_Builder.dtos.AuthDTOs;

import com.mrivals_builder.Mrivals_Builder.dtos.UserDTOs.UserDTO;

public record RegisterResponseDTO(String token, UserDTO user) {}
