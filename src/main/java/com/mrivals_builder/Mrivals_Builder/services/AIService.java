package com.mrivals_builder.Mrivals_Builder.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Service
public class AIService {

    private final WebClient webClient = WebClient.create();

    @Value("${ai.local.url}")
    private String aiBaseUrl;

    public Flux<String> askMarvelCompo(String prompt) {
        String url = aiBaseUrl + "/v1/chat/completions";

        Map<String, Object> body = Map.of(
                "model", "mistral-7b-instruct-v0.2.Q4_K_M",
                "messages", List.of(
                        Map.of("role", "user", "content",
                                "You are an expert at Marvel Rivals for team composition. 50 words max in two blocks: one for advantages and one for weaknesses. Analyze the potential synergies and weaknesses of this composition. " + prompt)
                ),
                "max_tokens", 1000,
                "temperature", 0.7,
                "stream", true
        );


        return webClient.post()
                .uri(aiBaseUrl + "/v1/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_NDJSON)
                .bodyValue(body)
                .retrieve()
                .bodyToFlux(String.class)
                .map(line -> {
                    return line;
                });
    }
}

