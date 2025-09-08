package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs;

public record LeaderboardPlayerDTO(
        Long player_uid,
        int matches,
        int wins,
        int kills,
        int deaths,
        int assists,
        int mvps,
        int svps
) {}
