package com.alsa.demo.entities;

import java.io.Serializable;

public class LeaguePositionId implements Serializable {

    public LeaguePositionId(Team team, League league) {
        this.team = team;
        this.league = league;
    }

    private Team team;
    private League league;

    public LeaguePositionId() {}

}
