package com.mrivals_builder.Mrivals_Builder.config;

import com.mrivals_builder.Mrivals_Builder.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskScheduler {

    @Autowired
    private HeroService heroService;

    @Scheduled(cron = "0 0 0 * * *")
    public void runEveryMinute() {
        heroService.getOrUpdateAllHeroData();
    }
}
