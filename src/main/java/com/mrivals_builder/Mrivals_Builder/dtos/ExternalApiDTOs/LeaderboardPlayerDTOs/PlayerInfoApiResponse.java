package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.LeaderboardPlayerDTOs;

public record PlayerInfoApiResponse(
        String name,
        PlayerIconApiResponse icon,
        PlayerRankSeasonApiResponse rank_season
) {
}
