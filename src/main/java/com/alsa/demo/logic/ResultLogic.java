package com.alsa.demo.logic;

import com.alsa.demo.entities.Result;
import com.alsa.demo.entities.Team;
import com.alsa.demo.exceptions.TeamNotFoundException;

import java.util.List;

public class ResultLogic {

    public static Result givenInputReturnResult(String score, List<Team> teams) throws TeamNotFoundException {
        String[] teamAndResult = score.split("-");
        int homeId = givenInputReturnTeamId(givenTeamAndResultReturnTeam(teamAndResult[0], true), teams);
        int awayId = givenInputReturnTeamId(givenTeamAndResultReturnTeam(teamAndResult[1], false), teams);
        int homeGoals = givenTeamAndResultReturnGoals(teamAndResult[0], true);
        int awayGoals = givenTeamAndResultReturnGoals(teamAndResult[1], false);
        return new Result(homeId, awayId, homeGoals, awayGoals);
    }

    public static String givenTeamAndResultReturnTeam(String input, boolean home) {
        int index = 1;
        if(home)
            index = 0;
        return input.split("\\d+")[index].strip();
    }

    public static int givenTeamAndResultReturnGoals(String input, boolean home) {
        int index = 0;
        if(home)
            index = 1;
        return Integer.parseInt(input.split("\\D+")[index]);
    }

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

    public static int givenInputReturnTeamId(String teamName, List<Team> teams) throws TeamNotFoundException {
        List<Integer> teamIdsWithName = teams.stream().filter(e -> e.getName().equals(teamName)).map(Team::getId).toList();
        if(teamIdsWithName.size() > 0)
            return teamIdsWithName.get(0);
        else
            throw new TeamNotFoundException(teamName);
    }

}
