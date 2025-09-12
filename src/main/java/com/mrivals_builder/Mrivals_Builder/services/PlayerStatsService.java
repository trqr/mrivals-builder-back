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

        String email = SecurityUtils.getCurrentUserEmail();
        User currentUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not Found!"));

        String statsRawJson = externalApiService.fetchUserPlayerStats(currentUser.getMrivalsAccount());

        PlayerStats createdOrUpdated = playerStatsRepository.findByUserId(currentUser.getId()).orElse(new PlayerStats());
        createdOrUpdated.setUserId(currentUser.getId());
        createdOrUpdated.setStatsRawJson(statsRawJson);
        return playerStatsRepository.save(createdOrUpdated);
    }
}
