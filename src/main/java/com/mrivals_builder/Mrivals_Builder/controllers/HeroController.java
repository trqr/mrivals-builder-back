package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.config.annotations.AdminOnly;
import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs.HeroDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTOs.HeroMainRoleRequestDTO;
import com.mrivals_builder.Mrivals_Builder.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @GetMapping("/update")
    public ResponseEntity<List<HeroDTO>> getAllHeroesFromApi(){
        return new ResponseEntity<>(heroService.getOrUpdateAllHeroData(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HeroDTO>> getAllHeroes(){
        return new ResponseEntity<>(heroService.getAllHeroes(), HttpStatus.OK);
    }

    @GetMapping("/{heroId}")
    public ResponseEntity<HeroDTO> getHeroById(@PathVariable Long heroId){
        return new ResponseEntity<>(heroService.getHeroById(heroId), HttpStatus.OK);
    }

    @PutMapping("/main-role")
    @AdminOnly
    public ResponseEntity<List<HeroDTO>> updateMainRole(@RequestBody HeroMainRoleRequestDTO request){
        return new ResponseEntity<>(heroService.updateMainRole(request), HttpStatus.CREATED);
    }
}
