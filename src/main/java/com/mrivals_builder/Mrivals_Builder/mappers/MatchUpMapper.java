package com.mrivals_builder.Mrivals_Builder.mappers;

import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs.HeroSummaryDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.MatchUpDTO;
import com.mrivals_builder.Mrivals_Builder.entities.MatchUp;

import java.util.List;

public class MatchUpMapper {

    public static List<MatchUpDTO> ListEntityToDTO(List<MatchUp> matchUps) {
        if (matchUps == null) return List.of();

        return matchUps.stream().map(MatchUpMapper::entityToDTO
        ).toList();
    }

    public static MatchUpDTO entityToDTO(MatchUp entity){
        return new MatchUpDTO(
                entity.getId(),
                entity.getHero().getId(),
                new HeroSummaryDTO(entity.getCounterPick().getId(), entity.getCounterPick().getName(), entity.getCounterPick().getImageLink()),
                entity.getValue()
        );
    }
}
