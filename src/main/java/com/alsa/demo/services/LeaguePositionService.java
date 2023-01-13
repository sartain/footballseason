package com.alsa.demo.services;

import com.alsa.demo.entities.*;
import com.alsa.demo.exceptions.LeagueNotFoundException;
import com.alsa.demo.exceptions.TeamNotFoundException;
import com.alsa.demo.logic.LeagueLogic;
import com.alsa.demo.logic.PremierLeagueScoringLogic;
import com.alsa.demo.logic.ResultLogic;
import com.alsa.demo.repositories.LeagueDao;
import com.alsa.demo.repositories.LeaguePositionDao;
import com.alsa.demo.repositories.TeamDao;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Thread.sleep;

@Service
public class LeaguePositionService {

    private static final Logger log = LoggerFactory.getLogger(LeaguePositionService.class);

    @Autowired
    LeaguePositionDao leaguePositionDao;

    @Autowired
    LeagueDao leagueDao;

    @Autowired
    TeamDao teamDao;

    public LeaguePositionService(LeaguePositionDao dao, TeamDao teamDao, LeagueDao leagueDao) {
        this.leaguePositionDao = dao;
        this.leagueDao = leagueDao;
        this.teamDao = teamDao;
    }

    public Optional<LeaguePosition> getLeaguePositionGivenTeamAndLeagueId(int teamId, int leagueId) {
        Optional<Team> t = teamDao.findById(teamId);
        Optional<League> l = leagueDao.findById(leagueId);
        return leaguePositionDao.findById(new LeaguePositionId(t.get(), l.get()));
    }

    public Optional<LeaguePosition> getLeaguePositionGivenTeamInLeague(String teamName, String leagueName) throws LeagueNotFoundException, TeamNotFoundException, InterruptedException {
        Optional<Team> t = teamDao.findByName(teamName);
        Optional<League> l = leagueDao.findByName(leagueName);
        if(t.isEmpty())
            throw new TeamNotFoundException(teamName);
        if(l.isEmpty())
            throw new LeagueNotFoundException(leagueName);
        else
            return leaguePositionDao.findById(new LeaguePositionId(t.get(), l.get()));
    }

    public List<LeaguePosition> getLeagueTable(int id) {
        return LeagueLogic.applyLeagueUpdate(leaguePositionDao.findAllByLeagueId(id));
    }

    @Observed(name = "get-league-table-given-name",
            contextualName = "receiving-league-table",
            lowCardinalityKeyValues = {"leagueName", "Premier League"})
    public Optional<List<LeaguePosition>> getLeagueTableGivenName(String leagueName) throws LeagueNotFoundException {
        Optional<League> l = leagueDao.findByName(leagueName);
        if(l.isEmpty())
            throw new LeagueNotFoundException(leagueName);
        else
            return Optional.of(LeagueLogic.applyLeagueUpdate(leaguePositionDao.findAllByLeagueId(l.get().getId())));
    }

    public void applyLeagueTableUpdateGivenScore(String match, String leagueName) throws TeamNotFoundException, LeagueNotFoundException {
        //Assumption is that this is premier league
        //Slightly inefficient as all teams in all positions are modified, not just ones referenced
        log.info(String.format("Updating league table for match: %s, in league: %s", match, leagueName));
        Optional<League> league = leagueDao.findByName(leagueName);
        if(league.isEmpty())
            throw new LeagueNotFoundException(leagueName);
        else {
            List<LeaguePosition> leaguePositions = getLeagueTable(league.get().getId());
            List<Team> positions = leaguePositions.stream().map(LeaguePosition::getTeam).toList();
            ResultUpdate[] r = PremierLeagueScoringLogic.scoreGame(ResultLogic.givenInputReturnResult(match, positions));
            LeagueLogic.applyResultUpdate(leaguePositions.stream().filter(e -> e.getTeam().getId().equals(r[0].getTeamId())).toList().get(0), r[0]);
            LeagueLogic.applyResultUpdate(leaguePositions.stream().filter(e -> e.getTeam().getId().equals(r[1].getTeamId())).toList().get(0), r[1]);
            LeagueLogic.applyLeagueUpdate(leaguePositions);
            for (LeaguePosition p : leaguePositions) {
                leaguePositionDao.save(p);
            }
        }
    }

}
