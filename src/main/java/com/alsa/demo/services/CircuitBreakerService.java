package com.alsa.demo.services;

import com.alsa.demo.entities.LeaguePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class CircuitBreakerService {

    private final WebClient client;

    @Autowired
    public CircuitBreakerService(WebClient.Builder webClientBuilder) {
        this.client = webClientBuilder.baseUrl("http://localhost:7654/leagueposition").build();
    }

    public Optional<LeaguePosition> getLeaguePositionGivenTeamAndLeagueName(String teamName, String leagueName) {
        String url = String.format("/%s/%s", leagueName, teamName);
        Mono<LeaguePosition> response = client.get().uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(LeaguePosition.class);
        return Optional.ofNullable(response.block());
    }

}
