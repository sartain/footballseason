package com.alsa.demo.logic;

import com.alsa.demo.entities.Result;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultLogicTests {

    private final String homeWin = "Everton 3-0 Liverpool";
    private final String draw = "Tottenham 1-1 Arsenal";
    private final String multiName = "Manchester United 2-1 Manchester City Women";
    private final String multiScore = "Vision Express 20-20 Specsavers";


    @Test
    void getTeamNamesSingleTeamNames() {
        String homeName = ResultLogic.givenInputReturnHomeTeam(homeWin);
        assertEquals("Everton", homeName);
        String awayName = ResultLogic.givenInputReturnAwayTeam(draw);
        assertEquals("Arsenal", awayName);
    }

    @Test
    void getMultiNameHomeTeamName() {
        String homeName = ResultLogic.givenInputReturnHomeTeam(multiName);
        assertEquals("Manchester United", homeName);
    }

    @Test
    void getMultiNameAwayTeamName() {
        String homeName = ResultLogic.givenInputReturnAwayTeam(multiName);
        assertEquals("Manchester City Women", homeName);
    }

}
