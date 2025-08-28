package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.CompoDTOs.BestWinRateByRoleDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Compo;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.repositories.CompoRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<BestWinRateByRoleDTO> getBestWinRateByRole(List<Long> heroesIds){

      //  Compo compo = compoRepository.findById(compoId)
       //         .orElseThrow(() -> new NotFoundException("Compo with id " + compoId + " not found"));

      //  List<Hero> compoHeroes = compo.getHeroes();
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

    public Compo create(Principal principal) {
        Compo created = new Compo();

        return compoRepository.save(new Compo());
    }
}
