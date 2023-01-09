package com.alsa.demo.logic;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.ResultUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeagueLogicTests {
    /* ToDo
        -Increment Games Played
        -Move above team, team moving above move below =
        -Move below team, team moving below move above =
        -Level points, better goal difference wins =
     */

    private LeagueLogic logic;
    private List<LeaguePosition> positions;
    private final int FIRST = 1;
    private final int SECOND = 2;
    private final int THIRD = 3;
    private final int FOURTH = 4;

    @BeforeEach
    void before() {
        logic = new LeagueLogic();
        positions = new ArrayList<>();
        positions.add(0, new LeaguePosition(1, 1, 1, 18, 40, 20, 19));
        positions.add(1, new LeaguePosition(2, 1, 2, 18, 38, 18, 17));
        positions.add(2, new LeaguePosition(3, 1, 3, 18, 38, 16, 16));
        positions.add(3, new LeaguePosition(4, 1, 4, 18, 36, 14, 13));
    }

    @Test
    void updateLeaguePositionVariableGivenResultUpdate() {
        LeaguePosition teamInFirst = positions.get(0);
        int expectedPoints = teamInFirst.getPoints() + 3;
        int expectedMatchPlayed = teamInFirst.getMatchesPlayed() + 1;
        int expectedGoalsFor = teamInFirst.getGoalsFor() + 4;
        int expectedGoalsAgainst = teamInFirst.getGoalsAgainst() + 1;
        LeaguePosition position = logic.applyResultUpdate(teamInFirst, new ResultUpdate(FIRST, 3, 4, 1));
        assertEquals(expectedPoints, position.getPoints());
        assertEquals(expectedMatchPlayed, position.getMatchesPlayed());
        assertEquals(expectedGoalsFor, position.getGoalsFor());
        assertEquals(expectedGoalsAgainst, position.getGoalsAgainst());
    }

}
