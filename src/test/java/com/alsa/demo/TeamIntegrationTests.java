package com.alsa.demo;
import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.Team;
import com.alsa.demo.input.TeamForm;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class TeamIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void saveTeam() {
        restTemplate.postForEntity("/team", new TeamForm("New Team"), TeamForm.class);
    }

    @Test
    public void getTeam() {
        ResponseEntity<Team> response = restTemplate.getForEntity("/team/Arsenal", Team.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        String teamName = response.getBody().getName();
        assertEquals("Arsenal", teamName);
    }

    @Test
    public void saveAndGetTeam() {
        restTemplate.postForEntity("/team", new TeamForm("New Team 2"), TeamForm.class);
        ResponseEntity<Team> response = restTemplate.getForEntity("/team/New Team 2", Team.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        String teamName = response.getBody().getName();
        assertEquals("New Team 2", teamName);
    }

    @Test
    public void saveAndGetTeamValidationIssue() {
        ResponseEntity r = restTemplate.postForEntity("/team", new TeamForm(""), TeamForm.class);
        assertEquals(r.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
