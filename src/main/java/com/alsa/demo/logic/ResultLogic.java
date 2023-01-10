package com.alsa.demo.logic;

import com.alsa.demo.entities.Result;

public class ResultLogic {

    public static String givenInputReturnHomeTeam(String score) {
        //Format = NAMEspaceSCORE-SCOREspaceNAME where underscore = space
        String homeTeam = score.split(" ")[0];
        return homeTeam;
    }

    public static String givenInputReturnAwayTeam(String score) {
        //Format = NAMEspaceSCORE-SCOREspaceNAME where underscore = space
        String homeTeam = score.split(" ")[2];
        return homeTeam;
    }

}
