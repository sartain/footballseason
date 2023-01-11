package com.alsa.demo.services;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class TeamServiceTests {

    @Autowired
    private TeamService service;

    @Test
    void testGetTeamNameFromId() {
        String expectedTeamName = "AFC Bournemouth";
        String actualTeamName = service.getTeamFromId(1).getName();
        assertEquals(expectedTeamName, actualTeamName);
    }

    @Test
    void saveTeam() {
        String expectedTeamName = "AFC Bournemouth";
        service.saveTeam(expectedTeamName);
        String actualTeamName = service.getTeamFromId(1).getName();
        assertEquals(expectedTeamName, actualTeamName);
    }

}
