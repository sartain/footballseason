package com.alsa.demo.services;

import com.alsa.demo.entities.League;
import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.LeaguePositionId;
import com.alsa.demo.entities.Team;
import com.alsa.demo.logic.LeagueLogic;
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
        Team t = teamDao.getReferenceById(teamId);
        League l = leagueDao.getReferenceById(leagueId);
        return leaguePositionDao.getReferenceById(new LeaguePositionId(t, l));
    }

    public LeaguePosition getLeaguePositionGivenTeamInLeague(String teamName, String leagueName) {
        Team t = teamDao.getReferenceByName(teamName);
        League l = leagueDao.getReferenceByName(leagueName);
        return leaguePositionDao.getReferenceById(new LeaguePositionId(t, l));
    }

    public List<LeaguePosition> getLeagueTable(int id) {
        return LeagueLogic.applyLeagueUpdate(leaguePositionDao.findAllByLeagueId(id));
    }

}
