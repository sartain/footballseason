package com.alsa.demo.config;

import com.alsa.demo.entities.League;
import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.Team;
import com.alsa.demo.repositories.LeagueDao;
import com.alsa.demo.repositories.LeaguePositionDao;
import com.alsa.demo.repositories.TeamDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private TeamDao teamDao;
    private LeagueDao leagueDao;
    private LeaguePositionDao leaguePositionDao;

    public AppInit(TeamDao teamDao, LeagueDao leagueDao, LeaguePositionDao leaguePositionDao) {
        this.teamDao = teamDao;
        this.leagueDao = leagueDao;
        this.leaguePositionDao = leaguePositionDao;
    }

    @Override
    public void run(String... args) {
        PremierLeagueImportData myPremierLeagueImportData = new PremierLeagueImportData();
        myPremierLeagueImportData.importData(teamDao, leagueDao, leaguePositionDao);
    }
}
