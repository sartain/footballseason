package com.alsa.demo;
import com.alsa.demo.entities.LeaguePosition;
import jakarta.servlet.ServletRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.net.URI;
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
        //Assert positions of teams
        ResponseEntity<LeaguePosition[]> response = restTemplate.getForEntity("/leagueposition/Premier League", LeaguePosition[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        List<LeaguePosition> leaguePositions = Arrays.asList(response.getBody());
        assertEquals("Manchester United", leaguePositions.get(3).getTeam().getName());
        assertEquals(4, leaguePositions.get(3).getPosition());
        assertEquals("Newcastle United", leaguePositions.get(2).getTeam().getName());
        assertEquals(3, leaguePositions.get(2).getPosition());
        //Make update
        ResponseEntity<String> postResponse = restTemplate.postForEntity("/leagueposition/Premier League", "Manchester United 2-0 Newcastle United", String.class);
        assert(postResponse.getBody().contains("Premier League"));
        //Assert updated position of teams
        response = restTemplate.getForEntity("/leagueposition/Premier League", LeaguePosition[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        leaguePositions = Arrays.asList(response.getBody());
        assertEquals("Manchester United", leaguePositions.get(2).getTeam().getName());
        assertEquals(3, leaguePositions.get(2).getPosition());
        assertEquals("Newcastle United", leaguePositions.get(3).getTeam().getName());
        assertEquals(4, leaguePositions.get(3).getPosition());
    }

    @Test
    public void failLeagueUpdateFalseTeamName() {
        ResponseEntity<String> postResponse = restTemplate.postForEntity("/leagueposition/Premier League", "Fake Name 2-0 Newcastle United", String.class);
        assertEquals(postResponse.getStatusCode(), HttpStatus.NOT_FOUND);
        assert(postResponse.getBody().contains("Fake Name"));
    }

    @Test
    public void failLeagueUpdateFalseLeagueName() {
        ResponseEntity<LeaguePosition[]> getResponse = restTemplate.getForEntity("/leagueposition/Premier League", LeaguePosition[].class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setBasicAuth("user", "password");
        HttpEntity<String> entity = new HttpEntity<>("Arsenal 2-0 Newcastle United", headers);
        ResponseEntity<String> postResponse = restTemplate.postForEntity("/leagueposition/Fake League", entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, postResponse.getStatusCode());
        assert(postResponse.getBody().contains("Fake League"));
        /*
        ResponseEntity<String> postResponse = restTemplate.postForEntity("/leagueposition/Fake League", "Arsenal 2-0 Newcastle United", String.class);
        assertEquals(postResponse.getStatusCode(), HttpStatus.NOT_FOUND);
        assert(postResponse.getBody().contains("Fake League"));
        ERROR data: null, error: org.springframework.security.web.csrf.InvalidCsrfTokenException: Invalid CSRF Token 'null' was found on the request parameter '_csrf' or header 'X-XSRF-TOKEN'.
         */
    }
}
