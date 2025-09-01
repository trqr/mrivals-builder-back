package com.mrivals_builder.Mrivals_Builder.dtos;

public record MatchUpDTO(
        Long id,
        HeroSummaryDTO counterPick,
        String value
) {}
