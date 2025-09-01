package com.mrivals_builder.Mrivals_Builder.mappers;

import com.mrivals_builder.Mrivals_Builder.dtos.AbilityDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Ability;

import java.util.List;

public class AbilityMapper {

    public static List<AbilityDTO> entityToDTO(List<Ability> abilities) {
        if (abilities == null) return List.of();

        return abilities.stream()
                .map(ability -> new AbilityDTO(
                        ability.getId(),
                        ability.getExternalId(),
                        ability.getName(),
                        ability.getType(),
                        ability.isCollab(),
                        ability.getDescription(),
                        ability.getIcon()
                )).toList();
    }
}
