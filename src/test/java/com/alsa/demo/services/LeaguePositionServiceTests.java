package com.alsa.demo.services;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class LeaguePositionServiceTests {


    @Autowired
    private LeaguePositionService service;

    @Test
    void getPositionOfTeamInLeague() {
        //Grab "Everton" from "Premier League"
        int actualPosition = service.getLeaguePositionGivenTeamInLeague(1, 1).getPosition();
        assertEquals(1, actualPosition);
    }


}
