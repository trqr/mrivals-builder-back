package com.mrivals_builder.Mrivals_Builder.mappers;


import com.mrivals_builder.Mrivals_Builder.dtos.AbilityDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.AbilityExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs.HeroDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.MatchUpDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Ability;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.repositories.AbilityRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HeroMapper {

    @Autowired
    private AbilityRepository abilityRepository;

    public Hero mapToEntity(HeroExternalApiDTO dto) {

        Hero hero = new Hero();

        hero.setExternalId(dto.id());
        hero.setName(dto.name());
        hero.setImageLink(dto.imageUrl());
        hero.setRole(dto.role());
        hero.setAttackType(dto.attack_type());
        hero.setDifficulty(dto.difficulty());
        hero.setBio(dto.bio());
        hero.setLore(dto.lore());
        hero.setMainTank(false);
        hero.setMainHeal(false);

        List<Ability> abilities = dto.abilities().stream()
                .map(abilityDto ->  {
                        Ability ability = abilityRepository.findAllByExternalId(abilityDto.id()).stream().findFirst()
                        .orElseGet(Ability::new);

        return mapAbilityToEntity(abilityDto, ability, hero);
                })
                .toList();

        hero.setAbilities(abilities);

        return hero;
    }

    private Ability mapAbilityToEntity(AbilityExternalApiDTO dto, Ability ability, Hero hero) {
        ability.setExternalId(dto.id());
        ability.setName(dto.name());
        ability.setType(dto.type());
        ability.setCollab(dto.collab());
        ability.setDescription(dto.description());
        ability.setIcon(dto.icon());
        ability.setHero(hero);
        return ability;
    }

    public static HeroDTO entityToDTO(Hero hero){
        List<AbilityDTO> abilities =  AbilityMapper.entityToDTO(hero.getAbilities());
        List<MatchUpDTO> matchUps = MatchUpMapper.entityToDTO(hero.getMatchUps());
        List<SynergieDTO> synergies = SynergieMapper.listEntityToDTO(hero.getSynergies());

        HeroDTO dto = new HeroDTO(
                hero.getId(),
                hero.getExternalId(),
                hero.getName(),
                hero.getImageLink(),
                hero.getRole(),
                hero.getAttackType(),
                hero.getDifficulty(),
                hero.getBio(),
                hero.getLore(),
                hero.getWinRate(),
                hero.isMainTank(),
                hero.isMainHeal(),
                abilities,
                synergies,
                matchUps
        );
        return dto;
    }
}

