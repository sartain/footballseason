package com.alsa.demo.controllers;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.exceptions.LeagueNotFoundException;
import com.alsa.demo.exceptions.TeamNotFoundException;
import com.alsa.demo.services.LeaguePositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
public class LeaguePositionController {
    private LeaguePositionService leaguePositionService;

    public LeaguePositionController(LeaguePositionService leaguePositionService) {
        this.leaguePositionService = leaguePositionService;
    }

    @GetMapping("leagueposition/{league}/{team}")
    public ResponseEntity<LeaguePosition> getLeaguePosition(
            @PathVariable String league,
            @PathVariable String team) throws LeagueNotFoundException, TeamNotFoundException {
        try {
            Optional<LeaguePosition> returnLeaguePosition = leaguePositionService.getLeaguePositionGivenTeamInLeague(team, league);
            return ResponseEntity.of(returnLeaguePosition);
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, e.getMessage(), e);
        }
    }




}
