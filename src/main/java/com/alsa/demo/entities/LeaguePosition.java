package com.alsa.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "leaguepositions")
@IdClass(LeaguePositionId.class)
public class LeaguePosition {

    @Id
    @OneToOne
    @JoinColumn(name = "teamId", referencedColumnName = "id")
    private Team team;
    @Id
    @ManyToOne
    @JoinColumn(name = "leagueId", referencedColumnName = "id")
    private League league;
    private Integer position;
    private Integer matchesPlayed;
    private Integer points;
    private Integer goalsFor;
    private Integer goalsAgainst;

    public LeaguePosition() {}

    public LeaguePosition(Team team, League league, Integer position, Integer matchesPlayed, Integer points, Integer goalsFor, Integer goalsAgainst) {
        this.team = team;
        this.league = league;
        this.position = position;
        this.matchesPlayed = matchesPlayed;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public LeaguePosition(int teamId, int leagueId, Integer position, Integer matchesPlayed, Integer points, Integer goalsFor, Integer goalsAgainst) {
        this.team = new Team(teamId, "example");
        this.league = new League(leagueId, "example");
        this.position = position;
        this.matchesPlayed = matchesPlayed;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
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

    /**
     * Contains Logic for comparing two league positions
     * 3 factors checked, if equal move to next
     *  -More points
     *  -Better Goal Difference
     *  -More Goals Scored
     *  -Return larger than as default
      * @param p LeaguePosition of other team
     * @return 1 for league position above next team, -1 if not
     */

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

    @Override
    public String toString() {
        return String.format(
                "Position: %d, TeamID: %s, GoalsFor: %d, GoalsAgainst: %d, Points: %d",
                this.getPosition(), this.getTeam(), this.getGoalsFor(), this.getGoalsAgainst(), this.getPoints()
        );
    }

}
