package com.mrivals_builder.Mrivals_Builder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaderboardService {

    @Autowired
    private ExternalApiService externalApiService;

    public void getHeroLeaderboard(Long heroId){
        externalApiService.getHeroLeaderboard(heroId);
    }
}
