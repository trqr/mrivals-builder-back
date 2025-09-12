package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.entities.PlayerStats;
import com.mrivals_builder.Mrivals_Builder.entities.User;
import com.mrivals_builder.Mrivals_Builder.exceptions.NotFoundException;
import com.mrivals_builder.Mrivals_Builder.repositories.PlayerStatsRepository;
import com.mrivals_builder.Mrivals_Builder.repositories.UserRepository;
import com.mrivals_builder.Mrivals_Builder.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatsService {

    @Autowired
    private ExternalApiService externalApiService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlayerStatsRepository playerStatsRepository;

    public PlayerStats saveUserPlayerStats(){
    User currentUser = getCurrentUser();

        String statsRawJson = externalApiService.fetchUserPlayerStats(currentUser.getMrivalsAccount());

        PlayerStats createdOrUpdated = playerStatsRepository.findByUserId(currentUser.getId()).orElse(new PlayerStats());
        createdOrUpdated.setUserId(currentUser.getId());
        createdOrUpdated.setStatsRawJson(statsRawJson);
        return playerStatsRepository.save(createdOrUpdated);
    }

    public String updatePlayerStats() {
        return externalApiService.updatePlayerStats(getCurrentUser().getMrivalsAccount());
    }

    public PlayerStats getUserPlayerStats() {
        User currentUser = getCurrentUser();

        return playerStatsRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new NotFoundException("Player stats for User ID " + currentUser.getId() + " not found."));
    }

    private User getCurrentUser(){
        String email = SecurityUtils.getCurrentUserEmail();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not Found!"));
    }
}
