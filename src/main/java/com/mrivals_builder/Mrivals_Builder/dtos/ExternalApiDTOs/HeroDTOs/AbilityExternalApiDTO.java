package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroDTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AbilityExternalApiDTO(
        Long id,
        String name,
        String type,
        @JsonProperty("isCollab")
        boolean collab,
        String description,
        String icon
) {}
