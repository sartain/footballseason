package com.alsa.demo.logic;

import com.alsa.demo.entities.Result;

import java.util.Arrays;

public class ResultLogic {

    public static String givenInputReturnHomeTeam(String score) {
        String homeTeamAndResult = score.split("-")[0];
        String homeTeam = homeTeamAndResult.split("\\d+")[0];
        return homeTeam.strip();
    }

    public static String givenInputReturnAwayTeam(String score) {
        String awayTeamAndResult = score.split("-")[1];
        String awayTeam = awayTeamAndResult.split("\\d+")[1];
        return awayTeam.strip();
    }

    public static int givenInputReturnHomeGoals(String score) {
        String homeTeamAndResult = score.split("-")[0];
        String homeGoals = homeTeamAndResult.split("\\D+")[1];
        return Integer.parseInt(homeGoals.strip());
    }

    public static int givenInputReturnAwayGoals(String score) {
        String homeTeamAndResult = score.split("-")[1];
        String homeGoals = homeTeamAndResult.split("\\D+")[0];
        return Integer.parseInt(homeGoals.strip());
    }

}
