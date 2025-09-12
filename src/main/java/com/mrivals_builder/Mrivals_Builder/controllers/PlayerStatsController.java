package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.entities.PlayerStats;
import com.mrivals_builder.Mrivals_Builder.services.PlayerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/player-stats")
public class PlayerStatsController {

    @Autowired
    private PlayerStatsService playerStatsService;

    public ResponseEntity<PlayerStats> saveUserPlayerStats(){
        return new ResponseEntity<>(playerStatsService.saveUserPlayerStats(), HttpStatus.CREATED);
    }
}
