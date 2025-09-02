package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.dtos.TeamDTOs.BestWinRateByRoleDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.TeamDTOs.TeamDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Team;
import com.mrivals_builder.Mrivals_Builder.services.TeamService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/compo")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/bestWinRateByRole")
    public ResponseEntity<List<BestWinRateByRoleDTO>> getBestWinRateByRole(@RequestBody List<Long> heroesIds){
        return new ResponseEntity<>(teamService.getBestWinRateByRole(heroesIds), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<TeamDTO> saveCompo(@RequestBody List<Long> heroesIds, @Nullable Principal principal) {
        return new ResponseEntity<>(teamService.saveTeamComposition(heroesIds, principal), HttpStatus.CREATED);
    }
}
