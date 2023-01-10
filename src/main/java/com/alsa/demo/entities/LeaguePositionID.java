package com.alsa.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(LeaguePositionID.class)
public class LeaguePositionID {

    public LeaguePositionID() {}

    public LeaguePositionID(Integer teamId, Integer leagueId, Integer position) {
        this.teamId = teamId;
        this.leagueId = leagueId;
        this.position = position;
    }

    @Id
    private Integer teamId;
    @Id
    private Integer leagueId;
    @Id
    private Integer position;

}
