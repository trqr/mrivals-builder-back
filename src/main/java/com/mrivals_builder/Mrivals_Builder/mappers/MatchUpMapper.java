package com.mrivals_builder.Mrivals_Builder.mappers;

import com.mrivals_builder.Mrivals_Builder.dtos.HeroSummaryDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.MatchUpDTO;
import com.mrivals_builder.Mrivals_Builder.entities.MatchUp;

import java.util.List;

public class MatchUpMapper {

    public static List<MatchUpDTO> entityToDTO(List<MatchUp> matchUps) {
        if (matchUps == null) return List.of();

        return matchUps.stream().map(matchUp -> new MatchUpDTO(
                matchUp.getId(),
                matchUp.getHero().getId(),
                new HeroSummaryDTO(matchUp.getCounterPick().getId(), matchUp.getCounterPick().getName(), matchUp.getCounterPick().getImageLink()),
                matchUp.getValue()
                )
        ).toList();
    }
}
