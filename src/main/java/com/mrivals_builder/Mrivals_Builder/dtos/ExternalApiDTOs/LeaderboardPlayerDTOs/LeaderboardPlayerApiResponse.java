package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.LeaderboardPlayerDTOs;

public record LeaderboardPlayerApiResponse(
        PlayerInfoApiResponse info,
        Long player_uid,
        int matches,
        int wins,
        int kills,
        int deaths,
        int assists,
        int mvps,
        int svps
) {
}
