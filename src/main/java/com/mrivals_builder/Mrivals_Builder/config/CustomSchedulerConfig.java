package com.mrivals_builder.Mrivals_Builder.config;

import com.mrivals_builder.Mrivals_Builder.services.HeroService;
import com.mrivals_builder.Mrivals_Builder.services.LeaderboardService;
import com.mrivals_builder.Mrivals_Builder.services.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class CustomSchedulerConfig {

    @Autowired
    private HeroService heroService;
    @Autowired
    private LeaderboardService leaderboardService;
    @Autowired
    private MapService mapService;

    @Scheduled(cron = "0 30 0 * * *")
    public void runEveryDayAt00h30() {
        mapService.getAllMapsFromApi();
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void runEveryDay() {
        heroService.getOrUpdateAllHeroData();
    }

    @Scheduled(cron = "0 30 23 * * *")
    public void runEveryDayAt23h30() {
        leaderboardService.fetchAndSaveAllLeaderboards();
    }
}
