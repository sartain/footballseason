package com.alsa.demo.entities;

public class Result {

    private Integer seasonId;
    private Integer homeTeamId;
    private Integer awayTeamId;
    private Integer homeGoals;
    private Integer awayGoals;

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public Integer getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(Integer homeGoals) {
        this.homeGoals = homeGoals;
    }

    public Integer getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(Integer awayGoals) {
        this.awayGoals = awayGoals;
    }

    public Result(int homeId, int awayId, int homeGoals, int awayGoals) {
        this.homeTeamId = homeId;
        this.awayTeamId = awayId;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    @Override
    public boolean equals(Object o) {
        if(!o.getClass().equals(this.getClass()))
            return false;
        else {
            Result r = (Result) o;
            return r.getHomeTeamId().equals(this.getHomeTeamId()) &&
                    r.getAwayTeamId().equals(this.getAwayTeamId()) &&
                    r.getHomeGoals().equals(this.getHomeGoals()) &&
                    r.getAwayGoals().equals(this.getAwayGoals());
        }
    }

}
