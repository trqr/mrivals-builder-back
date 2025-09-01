package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.ListedHero;
import com.mrivals_builder.Mrivals_Builder.dtos.HeroDTO;
import com.mrivals_builder.Mrivals_Builder.entities.Hero;
import com.mrivals_builder.Mrivals_Builder.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
