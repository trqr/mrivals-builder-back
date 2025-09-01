package com.mrivals_builder.Mrivals_Builder.dtos;

public record SynergieDTO(
        Long id,
        HeroSummaryDTO heroId,
        HeroSummaryDTO ally,
        String value,
        boolean isTeamUp
) {}

