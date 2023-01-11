package com.alsa.demo.controllers;

import com.alsa.demo.services.TeamService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    public TeamController(TeamService service) {
        this.service = service;
    }

    private TeamService service;


    @PostMapping("/team")
    public void saveTeam(@RequestBody String name) {
        service.saveTeam(name);
    }
}
