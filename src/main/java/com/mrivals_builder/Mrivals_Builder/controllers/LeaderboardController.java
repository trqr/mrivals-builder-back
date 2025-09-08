package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.dtos.LeaderboardDTOs.LeaderboardResponseDTO;
import com.mrivals_builder.Mrivals_Builder.services.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/leaderboard")
@RestController
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping("/{heroId}")
    public ResponseEntity<LeaderboardResponseDTO> getHeroLeaderboard(@PathVariable Long heroId){
        return new ResponseEntity<>(leaderboardService.getHeroLeaderboard(heroId), HttpStatus.OK);
  }
}
