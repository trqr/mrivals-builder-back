package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroDTOs;

import java.util.List;

public record HeroExternalApiDTO(
        Long id,
        String name,
        String real_name,
        String imageUrl,
        String role,
        String attack_type,
        String difficulty,
        String bio,
        String lore,
        List<AbilityExternalApiDTO> abilities
) {}
