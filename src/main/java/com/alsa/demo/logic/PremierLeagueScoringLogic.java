package com.alsa.demo.logic;

import com.alsa.demo.entities.Result;
import com.alsa.demo.entities.ResultUpdate;

import java.util.Objects;

public class PremierLeagueScoringLogic {

    private static final int DRAW = 1;
    private static final int WIN = 3;
    private static final int LOSE = 0;

    public static int[] scoreGame(int homeGoals, int awayGoals) {
        if(Objects.equals(homeGoals, awayGoals)) {
            return new int[] {DRAW, DRAW};
        }
        else if(homeGoals > awayGoals) {
            return new int[] {WIN, LOSE};
        }
        else {
            return new int[] {LOSE, WIN};
        }
    }

    public static ResultUpdate[] scoreGame(Result result) {
        int[] points = scoreGame(result.getHomeGoals(), result.getAwayGoals());
        ResultUpdate homeUpdate = new ResultUpdate(result.getHomeTeamId(), points[0], result.getHomeGoals(), result.getAwayGoals());
        ResultUpdate awayUpdate = new ResultUpdate(result.getAwayTeamId(), points[1], result.getAwayGoals(), result.getHomeGoals());
        return new ResultUpdate[] {homeUpdate, awayUpdate};
    }

}
