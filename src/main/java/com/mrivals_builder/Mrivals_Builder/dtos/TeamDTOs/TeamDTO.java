package com.mrivals_builder.Mrivals_Builder.dtos.TeamDTOs;

import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.HeroSummaryDTO;

import java.time.LocalDate;
import java.util.List;

public record TeamDTO(
        Long id, LocalDate createdDate, List<HeroDTO> heroes, Long userId
) {}
