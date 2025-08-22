package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs;

import com.mrivals_builder.Mrivals_Builder.entities.Ability;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeroExternalApiDTO {

    private String id;
    private String name;
    private String real_name;
    private String imageUrl;
    private String role;
    private String attack_type;
    private String difficulty;
    private String bio;
    private String lore;
    private List<Ability> abilities;
}
