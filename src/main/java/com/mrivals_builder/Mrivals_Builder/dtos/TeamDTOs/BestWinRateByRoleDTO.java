package com.mrivals_builder.Mrivals_Builder.dtos.TeamDTOs;

import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestWinRateByRoleDTO {
    private String role;
    private List<Hero> heroes;
}
