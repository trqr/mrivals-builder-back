package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.LeaderboardPlayerDTOs;

public record PlayerRankSeasonApiResponse(
        double rank_score,
        double max_rank_score,
        int win_count
) {
}
