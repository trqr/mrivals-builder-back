package com.mrivals_builder.Mrivals_Builder.mappers;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.LeaderboardPlayerDTOs.LeaderboardPlayerApiResponse;
import com.mrivals_builder.Mrivals_Builder.dtos.LeaderboardDTOs.LeaderboardPlayerDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.entities.LeaderboardPlayer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LeaderboardMapper {

    public List<LeaderboardPlayer> apiResponseToEntity(List<LeaderboardPlayerApiResponse> apiLeaderboard, Hero hero){
        return apiLeaderboard.stream().map(playerDto -> new LeaderboardPlayer(
                playerDto.info().name(),
                playerDto.info().icon().player_icon(),
                playerDto.info().rank_season().rank_score(),
                playerDto.info().rank_season().max_rank_score(),
                playerDto.info().rank_season().win_count(),
                hero,
                playerDto.matches(),
                playerDto.wins(),
                playerDto.kills(),
                playerDto.deaths(),
                playerDto.assists(),
                playerDto.mvps(),
                playerDto.svps())).toList();
    }

    public LeaderboardPlayerDTO entityToDTO(LeaderboardPlayer entity) {
        return new LeaderboardPlayerDTO(
                entity.getId(),
                entity.getName(),
                entity.getIcon(),
                entity.getRankScore(),
                entity.getMaxRankScore(),
                entity.getSeasonWinCount(),
                entity.getHero().getId(),
                entity.getHeroMatches(),
                entity.getHeroWins(),
                entity.getHeroKills(),
                entity.getHeroDeaths(),
                entity.getHeroAssists(),
                entity.getHeroMvps(),
                entity.getHeroSvps()
        );
    }
}
