package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroDTOs.ListedHero;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.LeaderboardPlayerDTOs.LeaderboardPlayerApiResponse;
import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs.HeroSummaryDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.LeaderboardDTOs.LeaderboardPlayerDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.LeaderboardDTOs.LeaderboardResponseDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.entities.LeaderboardPlayer;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.mappers.LeaderboardMapper;
import com.mrivals_builder.Mrivals_Builder.repositories.HeroRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.LeaderboardPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {

    @Autowired
    private ExternalApiService externalApiService;
    @Autowired
    private HeroRepository heroRepository;
    @Autowired
    private LeaderboardMapper leaderboardMapper;
    @Autowired
    private LeaderboardPlayerRepository leaderboardRepository;



    public List<LeaderboardPlayer> fetchAndSaveAllLeaderboards(){
        List<ListedHero> heroList = externalApiService.getHeroListFromApi();
        return heroList.stream().flatMap(hero -> fetchAndSaveHeroLeaderboard(hero.id()).stream()).toList();
    }

    public LeaderboardResponseDTO getHeroLeaderboard(Long heroId){

        Hero hero = heroRepository.findById(heroId)
                .orElseThrow(() -> new NotFoundException("Hero ID " + heroId + " not found"));
        HeroSummaryDTO heroDTO = new HeroSummaryDTO(hero.getId(), hero.getName(), hero.getImageLink());

        List<LeaderboardPlayer> players = leaderboardRepository.findByHero(hero);
        List<LeaderboardPlayerDTO> playersDTOs = players.stream().map(player -> leaderboardMapper.entityToDTO(player)).toList();

        return new LeaderboardResponseDTO(heroDTO, playersDTOs);
    }

    private List<LeaderboardPlayer> fetchAndSaveHeroLeaderboard(Long externalHeroId){
        List<LeaderboardPlayerApiResponse> leaderboard = externalApiService.getHeroLeaderboard(externalHeroId);
        Hero hero = heroRepository.findByExternalId(externalHeroId)
                .orElseThrow(() -> new NotFoundException("External Hero ID " + externalHeroId + " not found"));

        List<LeaderboardPlayer> players = leaderboardMapper.apiResponseToEntity(leaderboard, hero);
        return leaderboardRepository.saveAll(players);
    }

}
