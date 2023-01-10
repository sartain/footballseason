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
import java.util.stream.Collectors;

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

    public void applyLeagueTableUpdateGivenScore(String input) {
        //Assumption is that this is premier league
        List<LeaguePosition> leaguePositions = getLeagueTable(1);
        List<Team> positions = leaguePositions.stream().map(LeaguePosition::getTeamId).toList();
        try {
            ResultUpdate[] r = PremierLeagueScoringLogic.scoreGame(ResultLogic.givenInputReturnResult(input, positions));
            LeagueLogic.applyResultUpdate(leaguePositions.stream().filter(e -> e.getTeamId().getId().equals(r[0].getTeamId())).toList().get(0), r[0]);
            LeagueLogic.applyResultUpdate(leaguePositions.stream().filter(e -> e.getTeamId().getId().equals(r[1].getTeamId())).toList().get(0), r[1]);
            LeagueLogic.applyLeagueUpdate(leaguePositions);
            for(LeaguePosition p : leaguePositions) {
                System.out.println(p);
                leaguePositionDao.save(p);
            }
        }
        catch(Exception e) {
        }
    }

}
