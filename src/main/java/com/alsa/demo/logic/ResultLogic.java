package com.alsa.demo.logic;

import com.alsa.demo.entities.Result;

public class ResultLogic {

    public static String givenInputReturnHomeTeam(String score) {
        //Format = NAMEspaceSCORE-SCOREspaceNAME where underscore = space
        String homeTeamAndResult = score.split("-")[0];
        String homeTeam = score.split("\\d+")[0];
        return homeTeam.strip();
    }

    public static String givenInputReturnAwayTeam(String score) {
        //Format = NAMEspaceSCORE-SCOREspaceNAME where underscore = space
        String awayTeam = score.split(" ")[2];
        return awayTeam;
    }

}
