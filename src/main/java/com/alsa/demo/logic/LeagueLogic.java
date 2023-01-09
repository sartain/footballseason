package com.alsa.demo.logic;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.ResultUpdate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LeagueLogic {

    public LeaguePosition applyResultUpdate(LeaguePosition position, ResultUpdate update) {
        position.setPoints(position.getPoints() + update.getPoints());
        position.setGoalsFor(position.getGoalsFor() + update.getGoalsFor());
        position.setGoalsAgainst(position.getGoalsAgainst() + update.getGoalsAgainst());
        position.setMatchesPlayed(position.getMatchesPlayed() + 1);
        return position;
    }

    public LeaguePosition applyPositionUpdateMock(LeaguePosition position, List<LeaguePosition> positions) {
        int newPosition = (int) (positions.stream().filter(e -> e.getPoints() > position.getPoints()).count() + 1);
        position.setPosition(newPosition);
        return position;
    }

    public List<LeaguePosition> applyLeagueUpdate(LeaguePosition position, List<LeaguePosition> positions) {
        int currentPosition = position.getPosition();
        LeaguePosition newPosition = applyPositionUpdateMock(position, positions);
        //positions.set(positions.indexOf(newPosition), newPosition);
        int newPositionNumber = newPosition.getPosition();
        if(currentPosition == newPositionNumber) {
            return positions;
        }
        List<LeaguePosition> pos2 = positions.stream().sorted((e, f) -> f.getPoints().compareTo(e.getPoints())).collect(Collectors.toList());
        pos2.forEach(e -> e.setPosition(pos2.indexOf(e) + 1));
        return pos2;
    }

}
