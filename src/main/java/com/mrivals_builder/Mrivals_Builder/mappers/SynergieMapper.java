package com.mrivals_builder.Mrivals_Builder.mappers;

import java.util.List;

import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs.HeroSummaryDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTOs.SynergieRequestDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.entities.Synergie;
import org.springframework.stereotype.Component;

@Component
public class SynergieMapper {

    public static List<SynergieDTO> listEntityToDTO(List<Synergie> synergies) {
        if (synergies == null) return List.of();

        return synergies.stream().map(synergie -> entityToDTO(synergie)
        ).toList();
    }

    public Synergie RequestDTOtoEntity(SynergieRequestDTO requestDTO, Hero hero, Hero allyHero){
        Synergie created = new Synergie();
        created.setHero(hero);
        created.setAlly(allyHero);
        created.setValue(requestDTO.value());
        created.setTeamUp(requestDTO.isTeamup());
        return created;
    }

    public static SynergieDTO entityToDTO(Synergie synergie){
        return new SynergieDTO(
                synergie.getId(),
                synergie.getHero().getId(),
                new HeroSummaryDTO(synergie.getAlly().getId(), synergie.getAlly().getName(), synergie.getAlly().getImageLink()),
                synergie.getValue(),
                synergie.isTeamUp()
        );
    }
}
