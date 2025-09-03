package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.TeamDTOs.BestWinRateByRoleDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.TeamDTOs.TeamDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Team;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import com.mrivals_builder.Mrivals_Builder.exceptions.BadRequestException;
import com.mrivals_builder.Mrivals_Builder.mappers.HeroMapper;
import com.mrivals_builder.Mrivals_Builder.mappers.TeamMapper;
import com.mrivals_builder.Mrivals_Builder.repositories.TeamRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.HeroRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
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

    /*public List<TeamCounter> getTeamCounter(List<Long> heroesIds) {
        // 1) Récupérer tous les matchups
        List<MatchUp> allMatchups = new ArrayList<>();
        for (Long heroId : heroesIds) {
            Hero hero = heroRepository.findById(heroId).orElse(null);
            if (hero != null) {
                List<MatchUp> heroMatchups = matchUpRepository.findByHero(hero);
                allMatchups.addAll(heroMatchups);
            }
        }

        // 2) Grouper counter Id et calculer les pires matchups
        Map<Long, Int> enemyScores = allMatchups.stream()
                .collect(Collectors.groupingBy(
                        MatchUp::getCounterHeroId,
                        Collectors.summingInt(MatchUp::getValue)
                ));

        //3) trouver les plus gros contre à la composition grâce au somme récupérer plus tôt

        //4) retourner les contre de la composition
        return
    }*/


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
