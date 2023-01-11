package com.alsa.demo.services;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.exceptions.LeagueNotFoundException;
import com.alsa.demo.exceptions.TeamNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        int actualPosition = service.getLeaguePositionGivenTeamAndLeagueId(2, 1).get().getPosition();
        assertEquals(1, actualPosition);
    }

    @Test
    void getPositionOfTeamInLeagueViaTeamAndLeagueName() throws LeagueNotFoundException, TeamNotFoundException {
        //Arsenal of ID 2 are first
        int actualPosition = service.getLeaguePositionGivenTeamInLeague("Arsenal", "Premier League").get().getPosition();
        assertEquals(1, actualPosition);
    }

    @Test
    void applyScoreUpdate() throws LeagueNotFoundException, TeamNotFoundException {
        //Get previous positions
        int previousPositionManchesterUnited = service.getLeaguePositionGivenTeamInLeague("Manchester United", "Premier League").get().getPosition();
        int previousPositionNewcastleUnited = service.getLeaguePositionGivenTeamInLeague("Newcastle United", "Premier League").get().getPosition();
        assertEquals(3, previousPositionNewcastleUnited);
        assertEquals(4, previousPositionManchesterUnited);
        //Expectation is that Manchester overtake Newcastle
        //Apply update
        service.applyLeagueTableUpdateGivenScore("Manchester United 2-0 Newcastle United");
        //Check values
        int actualPositionManchesterUnited = service.getLeaguePositionGivenTeamInLeague("Manchester United", "Premier League").get().getPosition();
        int actualPositionNewcastleUnited = service.getLeaguePositionGivenTeamInLeague("Newcastle United", "Premier League").get().getPosition();
        assertEquals(previousPositionManchesterUnited, actualPositionNewcastleUnited);
        assertEquals(previousPositionNewcastleUnited, actualPositionManchesterUnited);
    }

    @Test
    void failFalseTeamName(){
        Exception e = assertThrows(TeamNotFoundException.class, () -> service.getLeaguePositionGivenTeamInLeague("Everton U23", "Premier League"));
        System.out.println(e.getMessage());
        assert(e.getMessage().contains("Everton U23"));
    }

    @Test
    void failFalseLeagueName(){
        Exception e = assertThrows(LeagueNotFoundException.class, () -> service.getLeaguePositionGivenTeamInLeague("Everton", "Fantasy League"));
        System.out.println(e.getMessage());
        assert(e.getMessage().contains("Fantasy League"));
    }
}
