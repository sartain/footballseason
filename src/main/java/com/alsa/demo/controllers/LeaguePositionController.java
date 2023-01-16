package com.alsa.demo.controllers;

import com.alsa.demo.config.SimpleHandler;
import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.services.LeaguePositionService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class LeaguePositionController {

    private ObservationRegistry prometheusRegistry;
    private LeaguePositionService leaguePositionService;

    public LeaguePositionController(ObservationRegistry registry, LeaguePositionService leaguePositionService) {
        this.prometheusRegistry = registry;
        this.prometheusRegistry.observationConfig().observationHandler(new SimpleHandler());
        this.leaguePositionService = leaguePositionService;
    }

    @GetMapping("leagueposition/{league}/{team}")
    public ResponseEntity<LeaguePosition> getLeaguePosition(
            @PathVariable String league,
            @PathVariable String team) {
        Observation.Context context = new Observation.Context().put(String.class, "get.league.position.given.league.and.team");
        Observation observation = Observation.start("getLeaguePositionGiveLeagueAndName", () -> context, prometheusRegistry);
        try (Observation.Scope scope = observation.openScope()){
            Optional<LeaguePosition> response = leaguePositionService.getLeaguePositionGivenTeamInLeague(team, league);
            return ResponseEntity.of(response);
        }
        catch(Exception e) {
            observation.error(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        finally {
            observation.stop();
        }
    }

    @GetMapping("leagueposition/{league}")
    public ResponseEntity<List<LeaguePosition>> getLeaguePositions(
            @PathVariable String league) {
        try {
            Optional<List<LeaguePosition>> returnLeaguePosition = leaguePositionService.getLeagueTableGivenName(league);
            return ResponseEntity.of(returnLeaguePosition);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PostMapping("leagueposition/{league}")
    public ResponseEntity<String> updateLeaguePositions(
            @PathVariable String league,
            @RequestBody String result) {
        try {
            leaguePositionService.applyLeagueTableUpdateGivenScore(result, league);
            return ResponseEntity.ok("Updated: " + league);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }





}
