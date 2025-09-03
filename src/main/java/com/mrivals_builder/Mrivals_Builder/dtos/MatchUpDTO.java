package com.mrivals_builder.Mrivals_Builder.dtos;

public record MatchUpDTO(
        Long id,
        Long heroId,
        HeroSummaryDTO counterPick,
        int value
) {}
