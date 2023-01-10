package com.alsa.demo.logic;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.ResultUpdate;

import java.util.List;
import java.util.stream.Collectors;

public class LeagueLogic {

    public static LeaguePosition applyResultUpdate(LeaguePosition position, ResultUpdate update) {
        position.setPoints(position.getPoints() + update.getPoints());
        position.setGoalsFor(position.getGoalsFor() + update.getGoalsFor());
        position.setGoalsAgainst(position.getGoalsAgainst() + update.getGoalsAgainst());
        position.setMatchesPlayed(position.getMatchesPlayed() + 1);
        return position;
    }

    /*
    Sort the list based on points, goal difference and goals scored
    Set position value of each team in the list to the sorted index +1 to avoid team in 0th place
     */

    public static List<LeaguePosition> applyLeagueUpdate(List<LeaguePosition> positions) {
        List<LeaguePosition> pos2 = positions.stream().sorted((e, f) -> f.compareTo(e)).collect(Collectors.toList());
        pos2.forEach(e -> e.setPosition(pos2.indexOf(e) + 1));
        return pos2;
    }

}
