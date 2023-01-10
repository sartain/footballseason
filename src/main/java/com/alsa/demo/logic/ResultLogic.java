package com.alsa.demo.logic;

import com.alsa.demo.entities.Result;

public class ResultLogic {

    public static String givenInputReturnHomeTeam(String score) {
        String homeTeamAndResult = score.split("-")[0];
        String homeTeam = score.split("\\d+")[0];
        return homeTeam.strip();
    }

    public static String givenInputReturnAwayTeam(String score) {
        String awayTeamAndResult = score.split("-")[1];
        String awayTeam = score.split("\\d+")[2];
        return awayTeam.strip();
    }

}
