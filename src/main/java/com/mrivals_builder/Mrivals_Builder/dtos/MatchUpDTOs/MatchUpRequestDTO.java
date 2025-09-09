package com.mrivals_builder.Mrivals_Builder.dtos.MatchUpDTOs;

public record MatchUpRequestDTO(
        Long heroId,
        Long counterPickId,
        int value
) {
}
