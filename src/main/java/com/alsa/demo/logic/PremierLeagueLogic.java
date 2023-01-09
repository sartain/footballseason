package com.alsa.demo.logic;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.Result;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PremierLeagueLogic {

    private final int DRAW = 1;
    private final int WIN = 3;
    private final int LOSE = 0;

    /*public List<LeaguePosition> increasePoints(List<LeaguePosition> positions, Result result) {
        int homePoints = LOSE;
        int awayPoints = LOSE;
        if(Objects.equals(result.getHomeGoals(), result.getAwayGoals())) {
            homePoints = DRAW;
            awayPoints = DRAW;
        }
        else if(result.getHomeGoals() > result.getAwayGoals()) {
            homePoints = WIN;
        }
        else {
            awayPoints = WIN;
        }
        List<LeaguePosition> newPositions = positions;
        //List after updating home team
        int finalHomePoints = homePoints;
        int finalHomePoints1 = homePoints;
    }*/

    public int[] scoreGame(int homeGoals, int awayGoals) {
        if(Objects.equals(homeGoals, awayGoals)) {
            return new int[] {DRAW, DRAW, 0, 0};
        }
        else if(homeGoals > awayGoals) {
            return new int[] {WIN, LOSE, homeGoals, -homeGoals};
        }
        else {
            return new int[] {LOSE, WIN, -awayGoals, awayGoals};
        }
    }

}
