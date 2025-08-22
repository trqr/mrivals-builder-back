package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroExternalApiDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroService {

    @Autowired
    private ExternalApiService externalApiService;

    public HeroExternalApiDTO fetchHeroFromExtApi(Long heroId){
        return externalApiService.getHeroFromApi(heroId);
    }
}
