package com.alsa.demo.services;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class LeagueServiceTests {

    @Autowired
    private LeagueService service;

    @Test
    void testGetTeamNameFromId() {
        String expectedLeagueName = "Premier League";
        String actualLeagueName = service.getLeagueFromId(1).getName();
        assertEquals(expectedLeagueName, actualLeagueName);
    }

}
