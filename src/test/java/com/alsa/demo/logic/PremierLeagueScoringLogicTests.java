package com.alsa.demo.logic;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.Result;
import com.alsa.demo.entities.ResultUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PremierLeagueScoringLogicTests {

    /* ToDo:
        -Win is 3 points to home team = Done
        -Goal difference applied to winning and losing teams = Done
        -Draw is 1 point to both teams = Done
        -Lose is 3 points to away team = Done
     */
    private List<LeaguePosition> position;
    private final int FIRST = 1;
    private final int SECOND = 2;
    private final int THIRD = 3;
    private final int FOURTH = 4;

    /**
     * Every team has a goal difference of 1 except team in third who are third due to goal difference
     * Team 2 and 3 have identical points
     * Other teams are 2 points (1 win) away from each other
     */

    @BeforeEach
    void before() {
        position = new ArrayList<>();
        position.add(0, new LeaguePosition(1, 1, 1, 18, 40, 20, 19));
        position.add(1, new LeaguePosition(2, 1, 2, 18, 38, 18, 17));
        position.add(2, new LeaguePosition(3, 1, 3, 18, 38, 16, 16));
        position.add(3, new LeaguePosition(4, 1, 4, 18, 36, 14, 13));
    }

    @Test
    void findWinnerHomeWin() {
        int[] actualResult = PremierLeagueScoringLogic.scoreGame(4, 0);
        assertEquals(3, actualResult[0]);
        assertEquals(0, actualResult[1]);
    }

    @Test
    void findWinnerAwayWin() {
        int[] actualResult = PremierLeagueScoringLogic.scoreGame(0, 4);
        assertEquals(0, actualResult[0]);
        assertEquals(3, actualResult[1]);
    }

    @Test
    void findDraw() {
        int[] actualResult = PremierLeagueScoringLogic.scoreGame(4, 4);
        assertEquals(1, actualResult[0]);
        assertEquals(1, actualResult[1]);
    }

    @Test
    void findLeagueUpdateForTeamOneGivenResult() {
        ResultUpdate[] actualResultUpdate = PremierLeagueScoringLogic.scoreGame(new Result(THIRD, SECOND, 4, 0));
        ResultUpdate homeTeamResultUpdate = actualResultUpdate[0];
        int actualTeamId = homeTeamResultUpdate.getTeamId();
        int actualPoints = homeTeamResultUpdate.getPoints();
        int actualGoalsFor = homeTeamResultUpdate.getGoalsFor();
        int actualGoalsAgainst = homeTeamResultUpdate.getGoalsAgainst();
        assertEquals(THIRD, actualTeamId);
        assertEquals(3, actualPoints);
        assertEquals(4, actualGoalsFor);
        assertEquals(0, actualGoalsAgainst);
    }

    @Test
    void findLeagueUpdateForTeamTwoGivenResult() {
        ResultUpdate[] actualResultUpdate = PremierLeagueScoringLogic.scoreGame(new Result(THIRD, SECOND, 4, 0));
        ResultUpdate awayTeamResultUpdate = actualResultUpdate[1];
        int actualTeamId = awayTeamResultUpdate.getTeamId();
        int actualPoints = awayTeamResultUpdate.getPoints();
        int actualGoalsFor = awayTeamResultUpdate.getGoalsFor();
        int actualGoalsAgainst = awayTeamResultUpdate.getGoalsAgainst();
        assertEquals(SECOND, actualTeamId);
        assertEquals(0, actualPoints);
        assertEquals(0, actualGoalsFor);
        assertEquals(4, actualGoalsAgainst);
    }

}
