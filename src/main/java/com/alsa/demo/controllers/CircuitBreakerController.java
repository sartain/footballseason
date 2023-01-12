package com.alsa.demo.controllers;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.services.CircuitBreakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class CircuitBreakerController {

    public CircuitBreakerController(CircuitBreakerService service) {
        this.service = service;
    }

    @Autowired
    CircuitBreakerService service;

    @GetMapping("circuitbreaker/{league}/{team}")
    public ResponseEntity<LeaguePosition> getLeaguePosition(
            @PathVariable String league,
            @PathVariable String team) {
        try {
            return ResponseEntity.of(service.getLeaguePositionGivenTeamAndLeagueName(team, league));
        }
        catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
