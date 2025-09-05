package com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTOs;

public record SynergieRequestDTO(
        Long heroId,
        Long allyId,
        int value,
        boolean isTeamup
) {
}
