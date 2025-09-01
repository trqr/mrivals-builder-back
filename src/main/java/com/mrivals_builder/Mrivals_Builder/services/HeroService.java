package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroStatsExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.ListedHero;
import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.mappers.HeroMapper;
import com.mrivals_builder.Mrivals_Builder.repositories.AbilityRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HeroService {

    @Autowired
    private ExternalApiService externalApiService;
    @Autowired
    private HeroRepository heroRepository;
    @Autowired
    private AbilityRepository abilityRepository;
    @Autowired
    private HeroMapper heroMapper;

    public Hero getOrUpdateHeroData(Long heroId){


        if (!heroRepository.existsByExternalId(heroId)) {
            HeroExternalApiDTO fetchedDTO = externalApiService.getHeroFromApi(heroId);

            Hero created = heroMapper.mapToEntity(fetchedDTO);
            updateStats(created);
            Hero saved = heroRepository.save(created);
            abilityRepository.saveAll(saved.getAbilities());

            return saved;
        }

        Hero hero = heroRepository.findByExternalId(heroId)
                .orElseThrow(() -> new RuntimeException("Hero with id " + heroId + " not found"));

        return updateStats(hero);
    }

    public List<HeroDTO> getOrUpdateAllHeroData() {
        List<ListedHero> heroList = externalApiService.getHeroListFromApi();

        List<Hero> heroes = new ArrayList<>();

        for (ListedHero listedHero : heroList) {
            heroes.add(getOrUpdateHeroData(listedHero.id()));
        }

        return heroes.stream().map(hero -> heroMapper.entityToDTO(hero)).toList();
    }

    public List<HeroDTO> getAllHeroes(){

        List<Hero> heroes = heroRepository.findAll();

        return heroes.stream().map(hero -> heroMapper.entityToDTO(hero)).toList();
    }

    private Hero updateStats(Hero hero){
        HeroStatsExternalApiDTO heroStatsDto = externalApiService.getHeroStatsFromApi(hero.getExternalId());

        double winRate = (double) heroStatsDto.wins() / heroStatsDto.matches();

        hero.setWinRate(winRate);

        return hero;
    }

    public HeroDTO getHeroById(Long heroId) {
        Hero hero = heroRepository.findById(heroId)
                .orElseThrow(() -> new NotFoundException("Hero with id " + heroId + " not found"));

        return heroMapper.entityToDTO(hero);
    }
}
