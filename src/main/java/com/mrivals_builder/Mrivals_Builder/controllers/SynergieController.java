package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.config.annotations.AdminOnly;
import com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.SynergieDTOs.SynergieRequestDTO;
import com.mrivals_builder.Mrivals_Builder.services.SynergieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/synergies")
public class SynergieController {

    @Autowired
    private SynergieService synergieService;

    @PostMapping
    @AdminOnly
    public ResponseEntity<SynergieDTO> addSynergie(@RequestBody SynergieRequestDTO requestDTO){
        return new ResponseEntity<>(synergieService.addSynergie(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @AdminOnly
    public ResponseEntity<SynergieDTO> updateSynergie(@PathVariable Long id, @RequestBody SynergieRequestDTO requestDTO){
        return new ResponseEntity<>(synergieService.updateSynergie(id, requestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @AdminOnly
    public ResponseEntity<Void> deleteSynergie(@PathVariable Long id){
        return synergieService.deleteSynergie(id);
    }
}
