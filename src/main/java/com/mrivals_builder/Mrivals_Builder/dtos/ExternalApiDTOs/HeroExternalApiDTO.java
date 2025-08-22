package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeroExternalApiDTO {

    private Long id;
    private String name;
    private String real_name;
    private String imageUrl;
    private String role;
    private String attack_type;
    private String difficulty;
    private String bio;
    private String lore;
    private List<AbilityExternalApiDTO> abilities;

}
