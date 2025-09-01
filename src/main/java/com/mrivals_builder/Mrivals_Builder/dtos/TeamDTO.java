package com.mrivals_builder.Mrivals_Builder.dtos;

import java.time.LocalDate;
import java.util.List;

public record TeamDTO(
        Long id, LocalDate createdDate, List<HeroSummaryDTO> heroes, Long userId
) {}
