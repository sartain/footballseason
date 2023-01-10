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
    void getPositionOfTeamInLeagueViaId() {
        //Arsenal of ID 2 are first
        int actualPosition = service.getLeaguePositionGivenTeamAndLeagueId(2, 1).getPosition();
        assertEquals(1, actualPosition);
    }

    @Test
    void getPositionOfTeamInLeagueViaTeamAndLeagueName() {
        //Arsenal of ID 2 are first
        int actualPosition = service.getLeaguePositionGivenTeamInLeague("Arsenal", "Premier League").getPosition();
        assertEquals(1, actualPosition);
    }

    @Test
    void applyScoreUpdate() {
        //Get previous positions
        int previousPositionManchesterUnited = service.getLeaguePositionGivenTeamInLeague("Manchester United", "Premier League").getPosition();
        int previousPositionNewcastleUnited = service.getLeaguePositionGivenTeamInLeague("Newcastle United", "Premier League").getPosition();
        assertEquals(3, previousPositionNewcastleUnited);
        assertEquals(4, previousPositionManchesterUnited);
        //Expectation is that Manchester overtake Newcastle
        //Apply update
        service.applyLeagueTableUpdateGivenScore("Manchester United 2-0 Newcastle United");
        //Check values
        int actualPositionManchesterUnited = service.getLeaguePositionGivenTeamInLeague("Manchester United", "Premier League").getPosition();
        int actualPositionNewcastleUnited = service.getLeaguePositionGivenTeamInLeague("Newcastle United", "Premier League").getPosition();
        assertEquals(previousPositionManchesterUnited, actualPositionNewcastleUnited);
        assertEquals(previousPositionNewcastleUnited, actualPositionManchesterUnited);
    }

}
