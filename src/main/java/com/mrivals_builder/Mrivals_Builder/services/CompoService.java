package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.CompoDTOs.BestWinRateByRoleDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Compo;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import com.mrivals_builder.Mrivals_Builder.exceptions.BadRequestException;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.repositories.CompoRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.HeroRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CompoService {

    @Autowired
    private HeroService heroService;

    @Autowired
    private CompoRepository compoRepository;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private UserRepository userRepository;

    public List<BestWinRateByRoleDTO> getBestWinRateByRole(List<Long> heroesIds){
        List<Hero> compoHeroes = heroRepository.findAllById(heroesIds);

        List<Hero> allHeroes = heroService.getAllHeroes();

        Set<Long> excludedIds = compoHeroes.stream()
                .map(Hero::getId)
                .collect(Collectors.toSet());

        List<Hero> availableHeroes = allHeroes.stream()
                .filter(hero -> !excludedIds.contains(hero.getId()))
                .toList();

        Map<String, List<Hero>> bestByRole = availableHeroes.stream()
                .collect(Collectors.groupingBy(
                        Hero::getRole,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparingDouble(Hero::getWinRate).reversed())
                                        .limit(2)
                                        .toList()
                        )
                ));
        return bestByRole.entrySet().stream()
                .map(entry -> new BestWinRateByRoleDTO(entry.getKey(), entry.getValue()))
                .toList();
    }


    public Compo saveCompo(List<Long> heroesIds, Principal principal) {
        if (heroesIds.size() > 6) {
            throw new BadRequestException("You can't save a compo with more than 6 heroes");
        }

        List<Hero> heroes = heroRepository.findAllById(heroesIds);

        User user = userRepository.findByEmail(principal.getName());

        Compo created = new Compo();
        created.setHeroes(heroes);
        created.setUser(user);

        return compoRepository.save(created);
    }
}
