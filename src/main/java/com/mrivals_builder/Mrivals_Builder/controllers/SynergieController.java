package com.mrivals_builder.Mrivals_Builder.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/synergie")
public class SynergieController {

    @GetMapping
    public String getSynergie(){
        return "Synergie";
    }
}
