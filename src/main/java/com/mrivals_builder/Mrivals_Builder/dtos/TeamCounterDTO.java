package com.mrivals_builder.Mrivals_Builder.dtos;

public record TeamCounterDTO (
    Long enemyHeroId,
    int totalScore,
    String name,
    String imageLink
) {}
