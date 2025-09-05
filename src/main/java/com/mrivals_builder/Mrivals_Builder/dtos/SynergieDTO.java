package com.mrivals_builder.Mrivals_Builder.dtos;

import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs.HeroSummaryDTO;

public record SynergieDTO(
        Long id,
        Long heroId,
        HeroSummaryDTO ally,
        int value,
        boolean isTeamUp
) {}

