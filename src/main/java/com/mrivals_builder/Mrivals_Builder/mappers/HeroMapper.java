package com.mrivals_builder.Mrivals_Builder.mappers;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.AbilityExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Ability;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.repositories.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class HeroMapper {

    @Autowired
    private AbilityRepository abilityRepository;

    public Hero mapToEntity(HeroExternalApiDTO dto, Hero hero) {

        hero.setExternalId(dto.getId());
        hero.setName(dto.getName());
        hero.setImageLink(dto.getImageUrl());
        hero.setRole(dto.getRole());
        hero.setAttackType(dto.getAttack_type());
        hero.setDifficulty(dto.getDifficulty());
        hero.setBio(dto.getBio());
        hero.setLore(dto.getLore());

        List<Ability> abilities = dto.getAbilities().stream()
                .map(abilityDto ->  {
                        Ability ability = abilityRepository.findByExternalId(abilityDto.getId())
                        .orElseGet(Ability::new);

        return mapAbilityToEntity(abilityDto, ability, hero);
                })
                .toList();

        hero.setAbilities(abilities);

        return hero;
    }

    private Ability mapAbilityToEntity(AbilityExternalApiDTO dto, Ability ability, Hero hero) {
        ability.setExternalId(dto.getId());
        ability.setName(dto.getName());
        ability.setType(dto.getType());
        ability.setCollab(dto.isCollab());
        ability.setDescription(dto.getDescription());
        ability.setIcon(dto.getIcon());
        ability.setHero(hero);
        return ability;
    }
}

