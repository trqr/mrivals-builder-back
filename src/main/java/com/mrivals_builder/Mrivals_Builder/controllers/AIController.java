package com.mrivals_builder.Mrivals_Builder.controllers;

import com.mrivals_builder.Mrivals_Builder.services.AIService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping(value = "/marvel-compo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getMarvelCompo(@RequestBody String prompt) {
        return aiService.askMarvelCompo(prompt);
    }
}

