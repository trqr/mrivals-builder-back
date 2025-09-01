package com.mrivals_builder.Mrivals_Builder.dtos;

public record AbilityDTO(
        Long id,
        Long externalId,
        String name,
        String type,
        boolean collab,
        String description,
        String icon
) {}
