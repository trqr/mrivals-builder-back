package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.entities.Map;
import com.mrivals_builder.Mrivals_Builder.services.MapService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/maps")
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping("/update")
    public ResponseEntity<List<Map>> fetchAllMapsFromApi(){
        return new ResponseEntity<>(mapService.getAllMapsFromApi(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Map>> getAllMaps(){
        return new ResponseEntity<>(mapService.getAllMaps(), HttpStatus.OK);
    }
}
