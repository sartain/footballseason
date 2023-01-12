package com.alsa.demo.controllers;

import com.alsa.demo.entities.Team;
import com.alsa.demo.input.TeamForm;
import com.alsa.demo.services.TeamService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamController {

    public TeamController(TeamService service) {
        this.service = service;
    }

    private TeamService service;


    @PostMapping("/team")
    public void saveTeam(@Validated @RequestBody TeamForm teamForm) {
        service.saveTeam(teamForm.getTeamName());
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<Team> getTeam(
            @PathVariable String teamName
    ) {
        return ResponseEntity.of(service.findTeamFromName(teamName));
    }
}
