package com.alsa.demo;
import com.alsa.demo.entities.LeaguePosition;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }

    @Test
    public void falseLeagueInPosition() {
        ResponseEntity<LeaguePosition> response = restTemplate.getForEntity("/leagueposition/Fake League/Everton", LeaguePosition.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }

    @Test
    public void retrieveLeagueGivenLeagueName() {
        ResponseEntity<LeaguePosition[]> response = restTemplate.getForEntity("/leagueposition/Premier League", LeaguePosition[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        List<LeaguePosition> leaguePositions = Arrays.asList(response.getBody());
        leaguePositions.forEach(e-> assertEquals(e.getPosition(), leaguePositions.indexOf(e) + 1));
    }

    @Test
    public void updateLeagueTable() {
        ResponseEntity<LeaguePosition[]> response = restTemplate.getForEntity("/leagueposition/Premier League", LeaguePosition[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        List<LeaguePosition> leaguePositions = Arrays.asList(response.getBody());
        assertEquals("Manchester United", leaguePositions.get(3).getTeam().getName());
        assertEquals(4, leaguePositions.get(3).getPosition());
        assertEquals("Newcastle United", leaguePositions.get(2).getTeam().getName());
        assertEquals(3, leaguePositions.get(2).getPosition());
        //Make update
        restTemplate.postForEntity("/leagueposition/Premier League", "Manchester United 2-0 Newcastle United", String.class);
        response = restTemplate.getForEntity("/leagueposition/Premier League", LeaguePosition[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        leaguePositions = Arrays.asList(response.getBody());
        assertEquals("Manchester United", leaguePositions.get(2).getTeam().getName());
        assertEquals(3, leaguePositions.get(2).getPosition());
        assertEquals("Newcastle United", leaguePositions.get(3).getTeam().getName());
        assertEquals(4, leaguePositions.get(3).getPosition());
    }
}
