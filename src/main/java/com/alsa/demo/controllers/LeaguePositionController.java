package com.alsa.demo.controllers;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.exceptions.LeagueNotFoundException;
import com.alsa.demo.exceptions.TeamNotFoundException;
import com.alsa.demo.services.LeaguePositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class LeaguePositionController {
    private LeaguePositionService leaguePositionService;

    public LeaguePositionController(LeaguePositionService leaguePositionService) {
        this.leaguePositionService = leaguePositionService;
    }

    @GetMapping("leagueposition/{league}/{team}")
    public ResponseEntity<LeaguePosition> getLeaguePosition(
            @PathVariable String league,
            @PathVariable String team) {
        try {
            return ResponseEntity.of(leaguePositionService.getLeaguePositionGivenTeamInLeague(team, league));
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
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
