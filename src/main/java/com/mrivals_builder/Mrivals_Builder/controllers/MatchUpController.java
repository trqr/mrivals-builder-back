package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.config.annotations.AdminOnly;
import com.mrivals_builder.Mrivals_Builder.dtos.MatchUpDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.MatchUpDTOs.MatchUpRequestDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTOs.SynergieRequestDTO;
import com.mrivals_builder.Mrivals_Builder.services.MatchUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match-up")
public class MatchUpController {

    @Autowired
    private MatchUpService matchUpService;

    @PostMapping
    @AdminOnly
    public ResponseEntity<MatchUpDTO> addMatchUp(@RequestBody MatchUpRequestDTO requestDTO){
        return new ResponseEntity<>(matchUpService.addMatchUp(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @AdminOnly
    public ResponseEntity<MatchUpDTO> updateMatchUp(@PathVariable Long id, @RequestBody MatchUpRequestDTO requestDTO){
        return new ResponseEntity<>(matchUpService.updateMatchUp(id, requestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @AdminOnly
    public ResponseEntity<Void> deleteMatchUp(@PathVariable Long id){
        return matchUpService.deleteMatchUp(id);
    }
}
