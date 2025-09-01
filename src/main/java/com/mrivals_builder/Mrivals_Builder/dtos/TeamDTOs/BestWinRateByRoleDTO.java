package com.mrivals_builder.Mrivals_Builder.dtos.TeamDTOs;

import com.mrivals_builder.Mrivals_Builder.entities.Hero;

import java.util.List;

public record BestWinRateByRoleDTO(String role, List<Hero> heroes) {}
