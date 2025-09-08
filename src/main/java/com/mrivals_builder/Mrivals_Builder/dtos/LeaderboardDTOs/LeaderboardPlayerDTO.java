package com.mrivals_builder.Mrivals_Builder.dtos.LeaderboardDTOs;

public record LeaderboardPlayerDTO(
        Long id,
        String name,
        String icon,
        double rankScore,
        double maxRankScore,
        int seasonWinCount,
        Long heroId,
        int heroMatches,
        int heroWins,
        int heroKills,
        int heroDeaths,
        int heroAssists,
        int heroMvps,
        int heroSvps
) {
}
