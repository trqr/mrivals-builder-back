package com.mrivals_builder.Mrivals_Builder.mappers;

import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.TeamDTOs.TeamDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.entities.Team;

import java.util.List;

public class TeamMapper {

    public static TeamDTO entityToDTO(Team team){
        List<HeroDTO> heroes = team.getHeroes()
                .stream()
                .map(hero -> HeroMapper.entityToDTO(hero))
                .toList();

        return new TeamDTO(
                team.getId(),
                team.getCreatedDate(),
                heroes,
                team.getUser().getId());
    }
}
