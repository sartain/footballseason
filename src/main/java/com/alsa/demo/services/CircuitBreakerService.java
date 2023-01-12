package com.alsa.demo.services;

import com.alsa.demo.entities.League;
import com.alsa.demo.entities.LeaguePosition;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

@Service
public class CircuitBreakerService {

    private final WebClient client;

    /**
     * After a single call is over 2 seconds, put the circuit breaker in open state for 20s
     * Then semi-open
     * If 70% of the single calls are failing do it
     * https://resilience4j.readme.io
     * @param webClientBuilder WebClient to make API call
     */

    @Autowired
    public CircuitBreakerService(WebClient.Builder webClientBuilder) {
        this.client = webClientBuilder.baseUrl("http://localhost:7654/leagueposition").build();
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(70.0f)
                .waitDurationInOpenState(Duration.ofMillis(20000))
                .permittedNumberOfCallsInHalfOpenState(1)
                .slidingWindow(2, 1, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slowCallDurationThreshold(Duration.ofSeconds(2))
                .build();

// Create a CircuitBreakerRegistry with a custom global configuration
        CircuitBreakerRegistry circuitBreakerRegistry =
                CircuitBreakerRegistry.of(circuitBreakerConfig);
        circuitBreaker = circuitBreakerRegistry.circuitBreaker("don't go breaking my circuit");
    }

    CircuitBreaker circuitBreaker;

    public Optional<LeaguePosition> getLeaguePositionGivenTeamAndLeagueNameWithBreaker(String teamName, String leagueName) {
        Supplier<Optional<LeaguePosition>> supplier = () -> client.get().uri("/"+leagueName+"/"+teamName)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(LeaguePosition.class)
                .delayElement(Duration.ofSeconds(5))
                .blockOptional();
        Supplier<Optional<LeaguePosition>> decoratedSupplier = circuitBreaker.decorateSupplier(supplier);
        return decoratedSupplier.get();
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
