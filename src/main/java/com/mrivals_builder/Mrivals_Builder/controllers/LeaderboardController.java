package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.dtos.LeaderboardDTOs.LeaderboardResponseDTO;
import com.mrivals_builder.Mrivals_Builder.services.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/leaderboard")
@RestController
public class LeaderboardController {

    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping("/{heroId}")
    public ResponseEntity<LeaderboardResponseDTO> getHeroLeaderboard(
            @PathVariable Long heroId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return new ResponseEntity<>(leaderboardService.getHeroLeaderboard(heroId, page, size), HttpStatus.OK);
  }
}
