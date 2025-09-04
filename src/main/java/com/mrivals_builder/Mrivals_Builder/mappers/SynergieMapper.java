package com.mrivals_builder.Mrivals_Builder.mappers;

import java.util.List;

import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs.HeroSummaryDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Synergie;

public class SynergieMapper {

    public static List<SynergieDTO> entityToDTO(List<Synergie> synergies) {
        if (synergies == null) return List.of();

        return synergies.stream().map(synergie -> new SynergieDTO(
                synergie.getId(),
                synergie.getHero().getId(),
                new HeroSummaryDTO(synergie.getAlly().getId(), synergie.getAlly().getName(), synergie.getAlly().getImageLink()),
                synergie.getValue(),
                synergie.isTeamUp()
            )
        ).toList();
    }
}
