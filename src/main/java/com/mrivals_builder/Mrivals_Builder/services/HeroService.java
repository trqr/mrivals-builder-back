package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.ListedHero;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.mappers.HeroMapper;
import com.mrivals_builder.Mrivals_Builder.repositories.AbilityRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Hero saveFetchedHeroToEntity(Long heroId){

        HeroExternalApiDTO fetchedDTO = externalApiService.getHeroFromApi(heroId);

        Hero hero = heroRepository.findByExternalId(heroId)
                .orElseGet( () -> new Hero());

        Hero saved = heroRepository.save(heroMapper.mapToEntity(fetchedDTO, hero));
        abilityRepository.saveAll(saved.getAbilities());

        return saved;
    }

    public List<Hero> getAllHeroesFromApi() {
        List<ListedHero> heroList = externalApiService.getHeroListFromApi();

        return heroList.stream()
                .map(listedHero -> saveFetchedHeroToEntity(listedHero.getId()))
                .toList();
    }

    public List<Hero> getAllHeroes(){
        return heroRepository.findAll();
    }
}
