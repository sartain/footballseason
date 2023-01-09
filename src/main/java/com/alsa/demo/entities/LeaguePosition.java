package com.alsa.demo.entities;

public class LeaguePosition {

    private Integer teamId;
    private Integer leagueId;
    private Integer position;
    private Integer matchesPlayed;
    private Integer points;
    private Integer goalsFor;
    private Integer goalsAgainst;

    public LeaguePosition(Integer teamId, Integer leagueId, Integer position, Integer matchesPlayed, Integer points, Integer goalsFor, Integer goalsAgainst) {
        this.teamId = teamId;
        this.leagueId = leagueId;
        this.position = position;
        this.matchesPlayed = matchesPlayed;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(Integer matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(Integer goalsFor) {
        this.goalsFor = goalsFor;
    }

    public Integer getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(Integer goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int compareTo(LeaguePosition p) {
        if(p.getPoints() > this.getPoints())
            return -1;
        else if(p.getPoints() == this.getPoints()) {
            if(p.getGoalsFor() - p.getGoalsAgainst() > this.getGoalsFor() - this.getGoalsAgainst())
                return -1;
            else if(p.getGoalsFor() - p.getGoalsAgainst() < this.getGoalsFor() - this.getGoalsAgainst())
                return 1;
            else
                if(p.getGoalsFor() > this.getGoalsFor())
                    return -1;
                else
                    return 1;
        }
        else {
            return 1;
        }
    }



}
