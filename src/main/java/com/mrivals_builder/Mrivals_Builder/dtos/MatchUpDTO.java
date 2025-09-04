package com.mrivals_builder.Mrivals_Builder.dtos;

import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs.HeroSummaryDTO;

public record MatchUpDTO(
        Long id,
        Long heroId,
        HeroSummaryDTO counterPick,
        int value
) {}
