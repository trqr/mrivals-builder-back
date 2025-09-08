package com.mrivals_builder.Mrivals_Builder.dtos.LeaderboardDTOs;

import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs.HeroSummaryDTO;

import java.util.List;

public record LeaderboardResponseDTO(
        HeroSummaryDTO hero,
        List<LeaderboardPlayerDTO> players
) {
}
