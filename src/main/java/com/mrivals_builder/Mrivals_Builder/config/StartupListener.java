package com.mrivals_builder.Mrivals_Builder.config;


import com.mrivals_builder.Mrivals_Builder.services.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {

    @Autowired
    private LeaderboardService leaderboardService;


    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        leaderboardService.fetchAndSaveAllLeaderboards();
    }
}
