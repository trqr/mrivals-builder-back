package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroDTOs.HeroExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroDTOs.HeroStatsExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroDTOs.ListedHero;
import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs.HeroDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs.HeroMainRoleRequestDTO;
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
            Hero saved = heroRepository.save(created);
            abilityRepository.saveAll(saved.getAbilities());

            return saved;
        }

        Hero hero = heroRepository.findByExternalId(heroId)
                .orElseThrow(() -> new RuntimeException("Hero with id " + heroId + " not found"));

        return hero;
    }

    public List<HeroDTO> getOrUpdateAllHeroData() {
        List<ListedHero> heroList = externalApiService.getHeroListFromApi();

        List<Hero> heroes = new ArrayList<>();

        for (ListedHero listedHero : heroList) {
            heroes.add(getOrUpdateHeroData(listedHero.id()));
            
            // Délai de 800ms entre chaque héros pour éviter les erreurs 429
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interruption lors de l'import des héros", e);
            }
        }

        return heroes.stream().map(hero -> heroMapper.entityToDTO(hero)).toList();
    }

    public List<HeroDTO> getAllHeroes(){

        List<Hero> heroes = heroRepository.findAll();

        return heroes.stream().map(hero -> heroMapper.entityToDTO(hero)).toList();
    }

    public Hero updateStats(Hero hero){
        HeroStatsExternalApiDTO heroStatsDto = externalApiService.getHeroStatsFromApi(hero.getExternalId());

        double winRate = (double) heroStatsDto.wins() / heroStatsDto.matches();

        hero.setWinRate(winRate);
        heroRepository.save(hero);

        return hero;
    }

    public List<HeroDTO> updateAllHeroStats() {
        List<Hero> heroes = heroRepository.findAll();

        for (Hero hero : heroes) {
            updateStats(hero);
            
            // Délai de 800ms entre chaque mise à jour de stats pour éviter les erreurs 429
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interruption lors de la mise à jour des stats", e);
            }
        }

        return heroes.stream().map(hero -> heroMapper.entityToDTO(hero)).toList();
    }

    public HeroDTO getHeroById(Long heroId) {
        Hero hero = heroRepository.findById(heroId)
                .orElseThrow(() -> new NotFoundException("Hero with id " + heroId + " not found"));

        return heroMapper.entityToDTO(hero);
    }

    public List<HeroDTO> updateMainRole(HeroMainRoleRequestDTO request) {
        List<Hero> heroes = heroRepository.findAllById(request.ids());

        if (request.role().equals("mainTank")) {
            heroes.forEach(hero -> {
                    hero.setMainTank(true);
                    hero.setMainHeal(false);
            });
        }

        if (request.role().equals("mainHealer")) {
            heroes.forEach(hero -> {
                hero.setMainTank(false);
                hero.setMainHeal(true);
            });
        }

        if (request.role().equals("none")) {
            heroes.forEach(hero -> {
                hero.setMainTank(false);
                hero.setMainHeal(false);
            });
        }

        heroRepository.saveAll(heroes);

        return heroes.stream().map(hero -> heroMapper.entityToDTO(hero)).toList();
    }
}
