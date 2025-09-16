package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.dtos.TeamCounterDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.TeamDTOs.BestWinRateByRoleDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.TeamDTOs.TeamDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.TeamSynergieDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Team;
import com.mrivals_builder.Mrivals_Builder.services.TeamService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping("/teamCounter")
    public ResponseEntity<List<TeamCounterDTO>> getTeamCounter(@RequestBody List<Long> heroesIds){
        return new ResponseEntity<>(teamService.getTeamCounter(heroesIds), HttpStatus.OK);
    }

    @PostMapping("/teamSynergies")
    public ResponseEntity<List<TeamSynergieDTO>> getTeamSynergie(@RequestBody List<Long> heroesIds){
        return new ResponseEntity<>(teamService.getTeamSynergie(heroesIds), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<TeamDTO> saveCompo(@RequestBody List<Long> heroesIds, @Nullable Principal principal) {
        return new ResponseEntity<>(teamService.saveTeamComposition(heroesIds, principal), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getUserTeamCompos(Principal principal){
        return new ResponseEntity<>(teamService.getUserTeamCompos(principal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {return teamService.deleteTeam(id);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllTeam() {return teamService.deleteAllTeams();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        return new ResponseEntity<>(teamService.getTeamById(id), HttpStatus.OK);
    }

}
