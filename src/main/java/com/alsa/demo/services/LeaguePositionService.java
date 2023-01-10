package com.alsa.demo.services;

import com.alsa.demo.entities.League;
import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.LeaguePositionId;
import com.alsa.demo.entities.Team;
import com.alsa.demo.repositories.LeagueDao;
import com.alsa.demo.repositories.LeaguePositionDao;
import com.alsa.demo.repositories.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public LeaguePosition getLeaguePositionGivenTeamInLeague(int teamId, int leagueId) {
        Team t = teamDao.getReferenceById(teamId);
        League l = leagueDao.getReferenceById(leagueId);
        return leaguePositionDao.getReferenceById(new LeaguePositionId(t, l));
    }

}
