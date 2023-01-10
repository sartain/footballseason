package com.alsa.demo.services;

import com.alsa.demo.entities.LeaguePosition;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class LeaguePositionServiceTests {


    @Autowired
    private LeaguePositionService service;

    @Test
    void getOrderedPremierLeagueTable() {
        List<LeaguePosition> teams = service.getLeagueTable(1);
        teams.forEach(e -> assertEquals(teams.indexOf(e) + 1, e.getPosition()));
    }

    @Test
    void getPositionOfTeamInLeague() {
        //Arsenal of ID 2 are first
        int actualPosition = service.getLeaguePositionGivenTeamInLeague(2, 1).getPosition();
        assertEquals(1, actualPosition);
    }


}
