package com.alsa.demo.logic;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.ResultUpdate;

public class LeagueLogic {

    public LeaguePosition applyResultUpdate(LeaguePosition position, ResultUpdate update) {
        position.setPoints(position.getPoints() + update.getPoints());
        position.setGoalsFor(position.getGoalsFor() + update.getGoalsFor());
        position.setGoalsAgainst(position.getGoalsAgainst() + update.getGoalsAgainst());
        position.setMatchesPlayed(position.getMatchesPlayed() + 1);
        return position;
    }

}
