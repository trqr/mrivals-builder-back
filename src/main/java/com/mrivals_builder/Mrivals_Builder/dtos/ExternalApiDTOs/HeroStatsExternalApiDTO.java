package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeroStatsExternalApiDTO {

    private Long heroId;
    private String heroName;
    private String heroIcon;
    private Integer matches;
    private Integer wins;
    private Double k;
    private Double d;
    private Double a;
    private String playTime;
    private Double totalHeroDamage;
    private Double totalHeroHeal;
    private Double totalDamageTaken;
    private Double sessionHitRate;
    private Double soloKill;

}
