package com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroDTOs;

public record HeroStatsExternalApiDTO(
        Long heroId,
        String heroName,
        String heroIcon,
        Integer matches,
        Integer wins,
        Double k,
        Double d,
        Double a,
        String playTime,
        Double totalHeroDamage,
        Double totalHeroHeal,
        Double totalDamageTaken,
        Double sessionHitRate,
        Double soloKill
) {}
