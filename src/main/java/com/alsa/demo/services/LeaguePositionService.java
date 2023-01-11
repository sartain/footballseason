package com.alsa.demo.services;

import com.alsa.demo.entities.*;
import com.alsa.demo.logic.LeagueLogic;
import com.alsa.demo.logic.PremierLeagueScoringLogic;
import com.alsa.demo.logic.ResultLogic;
import com.alsa.demo.repositories.LeagueDao;
import com.alsa.demo.repositories.LeaguePositionDao;
import com.alsa.demo.repositories.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaguePositionService {

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

    public LeaguePosition getLeaguePositionGivenTeamAndLeagueId(int teamId, int leagueId) {
        Team t = teamDao.findById(teamId).get();
        League l = leagueDao.findById(leagueId).get();
        return leaguePositionDao.findById(new LeaguePositionId(t, l)).get();
    }

    public LeaguePosition getLeaguePositionGivenTeamInLeague(String teamName, String leagueName) {
        Team t = teamDao.findByName(teamName);
        League l = leagueDao.findByName(leagueName);
        return leaguePositionDao.findById(new LeaguePositionId(t, l)).get();
    }

    public List<LeaguePosition> getLeagueTable(int id) {
        return LeagueLogic.applyLeagueUpdate(leaguePositionDao.findAllByLeagueId(id));
    }

    public void applyLeagueTableUpdateGivenScore(String input) {
        //Assumption is that this is premier league
        //Slightly inefficient as all teams in all positions are modified, not just ones referenced
        List<LeaguePosition> leaguePositions = getLeagueTable(1);
        List<Team> positions = leaguePositions.stream().map(LeaguePosition::getTeamId).toList();
        try {
            ResultUpdate[] r = PremierLeagueScoringLogic.scoreGame(ResultLogic.givenInputReturnResult(input, positions));
            LeagueLogic.applyResultUpdate(leaguePositions.stream().filter(e -> e.getTeamId().getId().equals(r[0].getTeamId())).toList().get(0), r[0]);
            LeagueLogic.applyResultUpdate(leaguePositions.stream().filter(e -> e.getTeamId().getId().equals(r[1].getTeamId())).toList().get(0), r[1]);
            LeagueLogic.applyLeagueUpdate(leaguePositions);
            for(LeaguePosition p : leaguePositions) {
                leaguePositionDao.save(p);
            }
        }
        catch(Exception e) {
        }
    }

}
