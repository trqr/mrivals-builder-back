package com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs;

import com.mrivals_builder.Mrivals_Builder.dtos.AbilityDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.MatchUpDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTO;

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
        boolean isMainTank,
        boolean isMainHeal,
        List<AbilityDTO> abilities,
        List<SynergieDTO> synergies,
        List<MatchUpDTO> matchUps
) {}
