package com.alsa.demo;
import com.alsa.demo.entities.LeaguePosition;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class LeaguePositionIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void retrieveTeamInPosition() {
        ResponseEntity<LeaguePosition> response = restTemplate.getForEntity("/leagueposition/Premier League/Everton", LeaguePosition.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        String teamName = response.getBody().getTeam().getName();
        assertEquals("Everton", teamName);
        System.out.println(teamName);
    }

    @Test
    public void falseTeamInPosition() {
        ResponseEntity<LeaguePosition> response = restTemplate.getForEntity("/leagueposition/Premier League/Fake Name", LeaguePosition.class);
        assertEquals(HttpStatus.I_AM_A_TEAPOT, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }

    @Test
    public void falseLeagueInPosition() {
        ResponseEntity<LeaguePosition> response = restTemplate.getForEntity("/leagueposition/Fake League/Everton", LeaguePosition.class);
        assertEquals(HttpStatus.I_AM_A_TEAPOT, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }
}
