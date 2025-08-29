package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.dtos.CompoDTOs.BestWinRateByRoleDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Compo;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.exceptions.BadRequestException;
import com.mrivals_builder.Mrivals_Builder.services.CompoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/compo")
public class CompoController {

    @Autowired
    private CompoService compoService;

    @PostMapping("/bestWinRateByRole")
    public ResponseEntity<List<BestWinRateByRoleDTO>> getBestWinRateByRole(@RequestBody List<Long> heroesIds){
        return new ResponseEntity<>(compoService.getBestWinRateByRole(heroesIds), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Compo> saveCompo(@RequestBody List<Long> heroesIds, Principal principal) {
        return new ResponseEntity<>(compoService.saveCompo(heroesIds, principal), HttpStatus.CREATED);
    }
}
