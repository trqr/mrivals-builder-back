package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExternalApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.base.url}")
    private String baseUrl;

    public List<ListedHero> getHeroListFromApi(){
        String url = baseUrl + "/heroes";
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<ListedHero>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<ListedHero>>() {}
        );

        return response.getBody();
    }


    public HeroExternalApiDTO getHeroFromApi(Long heroId) {
        String url = baseUrl + "/heroes/hero/" + heroId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<HeroExternalApiDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                HeroExternalApiDTO.class
        );
        return response.getBody();
    }

    public HeroStatsExternalApiDTO getHeroStatsFromApi(Long heroId) {
        String url = baseUrl + "/heroes/hero/" + heroId + "/stats";
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        int retries = 0;
        while (true) {
            try {
                ResponseEntity<HeroStatsExternalApiDTO> response = restTemplate.exchange(
                        url, HttpMethod.GET, entity, HeroStatsExternalApiDTO.class);
                return response.getBody();
            } catch (HttpClientErrorException.TooManyRequests e) {
                retries++;
                if (retries > 5) throw e;
                try {
                    Thread.sleep(1000 * retries);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public List<MapExternalApiDTO> getMapsFromApi(){
        String url = baseUrl + "/maps";
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<MapApiResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                MapApiResponse.class
        );

        return response.getBody().maps();
    }
}
