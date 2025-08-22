package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.ListedHero;
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

    @GetMapping("/{heroId}")
    public ResponseEntity<Hero> fetchHeroFromExtApi(@PathVariable Long heroId){
        System.out.println("Fetching hero "+ heroId + " from external api");
        return new ResponseEntity<>(heroService.saveFetchedHeroToEntity(heroId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ListedHero>> getAllHeroesFromApi(){
        return new ResponseEntity<>(heroService.getAllHeroesFromApi(), HttpStatus.OK);
    }
}
