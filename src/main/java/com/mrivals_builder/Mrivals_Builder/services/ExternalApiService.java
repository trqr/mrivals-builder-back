package com.mrivals_builder.Mrivals_Builder.services;

import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroDTOs.HeroExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroDTOs.HeroStatsExternalApiDTO;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.HeroDTOs.ListedHero;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.LeaderboardPlayerDTOs.*;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.MapDTOs.MapApiResponse;
import com.mrivals_builder.Mrivals_Builder.dtos.ExternalApiDTOs.MapDTOs.MapExternalApiDTO;
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

    public List<LeaderboardPlayerApiResponse> getHeroLeaderboard(Long heroId){
        String url = baseUrl + "/heroes/leaderboard/" + heroId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<LeaderboardApiResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    LeaderboardApiResponse.class
            );
            System.out.println(response.getBody().players());

            return response.getBody().players();

        }   catch (RuntimeException ex) {
            System.err.println("API Marvel Rivals indisponible, fallback appliqué");
            LeaderboardPlayerApiResponse fallbackPlayer =
                    new LeaderboardPlayerApiResponse(
                            new PlayerInfoApiResponse(
                                    "",
                                    new PlayerIconApiResponse(""),
                                    new PlayerRankSeasonApiResponse(
                                            0,
                                            0,
                                            0)), 0L,0,0,0,0,0,0,0);
            return List.of(fallbackPlayer);
        }
    }

    public String fetchUserPlayerStats(String rivalsAccount){
        String url = "https://api.externe.com/data";
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}
