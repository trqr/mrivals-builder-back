package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.TeamCounterDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.TeamDTOs.BestWinRateByRoleDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.TeamDTOs.TeamDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.TeamSynergieDTO;
import com.mrivals_builder.Mrivals_Builder.entities.*;
import com.mrivals_builder.Mrivals_Builder.exceptions.BadRequestException;
import com.mrivals_builder.Mrivals_Builder.mappers.HeroMapper;
import com.mrivals_builder.Mrivals_Builder.mappers.TeamMapper;
import com.mrivals_builder.Mrivals_Builder.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private HeroService heroService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MatchUpRepository matchUpRepository;

    @Autowired
    private SynergieRepository synergieRepository;

    public List<BestWinRateByRoleDTO> getBestWinRateByRole(List<Long> heroesIds){
        List<Hero> teamHeroes = heroRepository.findAllById(heroesIds);

        List<Hero> allHeroes = heroRepository.findAll();

        Set<Long> excludedIds = teamHeroes.stream()
                .map(Hero::getId)
                .collect(Collectors.toSet());

        List<Hero> availableHeroes = allHeroes.stream()
                .filter(hero -> !excludedIds.contains(hero.getId()))
                .toList();

        Map<String, List<Hero>> bestByRole = new HashMap<>();

        Map<String, List<Hero>> groupedByRole = availableHeroes.stream()
                .collect(Collectors.groupingBy(Hero::getRole));

        groupedByRole.forEach((role, heroes) -> {
            List<Hero> bestHeroes = heroes.stream()
                    .sorted(Comparator.comparingDouble(Hero::getWinRate).reversed())
                    .limit(2)
                    .toList();

            bestByRole.put(role, bestHeroes);
        });

        return bestByRole.entrySet().stream()
                .map(entry -> new BestWinRateByRoleDTO(entry.getKey(), entry.getValue().stream()
                        .map(HeroMapper::entityToDTO)
                        .toList()))
                .toList();
    }

    public List<TeamCounterDTO> getTeamCounter(List<Long> heroesIds) {
    // 1) Charger tout les matchups des héros de la compo
    List<MatchUp> allMatchups = new ArrayList<>();
    for (Long heroId : heroesIds) {
        Hero hero = heroRepository.findById(heroId).orElse(null);
        if (hero != null) {
            List<MatchUp> heroMatchups = matchUpRepository.findByHero(hero);
            allMatchups.addAll(heroMatchups);
        }
    }

    // 2) Regrouper par counter et aditionner les valeurs
    Map<Hero, Integer> enemyScores = allMatchups.stream()
        .collect(Collectors.groupingBy(
            MatchUp::getCounterPick,
            Collectors.summingInt(MatchUp::getValue)
        ));

    // 3) Transformer le Map en List<TeamCounter>
    List<TeamCounterDTO> counters = enemyScores.entrySet().stream()
        .map(entry -> new TeamCounterDTO(entry.getKey().getId(), entry.getValue(), entry.getKey().getName(), entry.getKey().getImageLink()))
        .collect(Collectors.toList());

    // 4) Trier par score
    counters.sort(Comparator.comparingInt(TeamCounterDTO::totalScore));

    // 5) retourner les 5 pires contre
    return counters.stream().limit(5).toList();
    }

    public List<TeamSynergieDTO> getTeamSynergie(List<Long> heroesIds) {
        List<Synergie> allSynergies = new ArrayList<>();

        for(Long heroId : heroesIds) {
            Hero hero = heroRepository.findById(heroId).orElse(null);
            if(hero != null) {
                List<Synergie> heroSynergies = synergieRepository.findByHero(hero);
                allSynergies.addAll(heroSynergies);
            }
        }

        List<Synergie> availableSynergies = allSynergies.stream()
                .filter(synergie -> !heroesIds.contains(synergie.getAlly().getId()))
                .toList();

        Map<Hero, Integer> synergieScores = availableSynergies.stream()
                .collect(Collectors.groupingBy(
                        Synergie::getAlly,
                        Collectors.summingInt(Synergie::getValue)
                ));

        List<TeamSynergieDTO> allies = synergieScores.entrySet().stream()
                .map(entry -> new TeamSynergieDTO(entry.getKey().getId(), entry.getValue(), entry.getKey().getName(), entry.getKey().getImageLink()))
                .collect(Collectors.toList());

        allies.sort(Comparator.comparingInt(TeamSynergieDTO::totalScore).reversed());

        return allies.stream().limit(5).toList();
    }

    public TeamDTO saveTeamComposition(List<Long> heroesIds, Principal principal) {
        if (heroesIds.size() > 6) {
            throw new BadRequestException("You can't save a team composition with more than 6 heroes");
        }

        List<Hero> heroes = heroRepository.findAllById(heroesIds);

        Team created = new Team();
        created.setHeroes(heroes);

        User user = null;
        if (principal != null && principal.getName() != null) {
            user = userRepository.findByEmail(principal.getName()).orElse(null);
        }
        created.setUser(user);

        Team saved = teamRepository.save(created);

        return TeamMapper.entityToDTO(saved);
    }
}
