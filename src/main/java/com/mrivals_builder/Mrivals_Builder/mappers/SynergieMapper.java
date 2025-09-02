package com.mrivals_builder.Mrivals_Builder.mappers;

import java.util.List;

import com.mrivals_builder.Mrivals_Builder.dtos.HeroSummaryDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Synergie;

public class SynergieMapper {

    public static List<SynergieDTO> entityToDTO(List<Synergie> synergies) {
        if (synergies == null) return List.of();

        return synergies.stream().map(synergie -> new SynergieDTO(
                synergie.getId(),
                synergie.getHero().getId(),
                new HeroSummaryDTO(Synergie.getAlly().getId(), Synergie.getAlly().getName(), Synergie.getAlly().getImageLink()),
                synergie.getValue(),
                synergie.getIsTeamUp(),
            )
        ).toList();
    }
}
