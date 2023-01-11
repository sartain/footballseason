package com.alsa.demo.controllers;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.exceptions.LeagueNotFoundException;
import com.alsa.demo.exceptions.TeamNotFoundException;
import com.alsa.demo.services.LeaguePositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
        return ResponseEntity.of(leaguePositionService.getLeaguePositionGivenTeamInLeague(team, league));
    }




}
