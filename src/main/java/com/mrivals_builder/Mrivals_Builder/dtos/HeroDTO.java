package com.mrivals_builder.Mrivals_Builder.dtos;

import java.util.List;

public record HeroDTO(
        Long id,
        Long externalId,
        String name,
        String imageLink,
        String role,
        String attackType,
        String difficulty,
        String bio,
        String lore,
        double winRate,
        List<AbilityDTO> abilities,
        List<SynergieDTO> synergies,
        List<MatchUpDTO> matchUps
) {}
