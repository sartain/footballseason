package com.alsa.demo.controllers;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.services.LeaguePositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LeaguePositionController {
    private LeaguePositionService leaguePositionService;

    public LeaguePositionController(LeaguePositionService leaguePositionService) {
        this.leaguePositionService = leaguePositionService;
    }

    @GetMapping("leagueposition")
    public ResponseEntity<LeaguePosition> getLeaguePosition(
            @RequestParam String league,
            @RequestParam String team) {
        return ResponseEntity.ok(leaguePositionService.getLeaguePositionGivenTeamInLeague(team, league));
    }




}
