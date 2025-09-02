package com.mrivals_builder.Mrivals_Builder.dtos;

public record SynergieDTO(
        Long id,
        Long heroId,
        HeroSummaryDTO ally,
        String value,
        boolean isTeamUp
) {}

