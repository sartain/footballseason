package com.alsa.demo.config;

import com.alsa.demo.entities.League;
import com.alsa.demo.entities.Team;
import com.alsa.demo.repositories.LeagueDao;
import com.alsa.demo.repositories.TeamDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private TeamDao teamDao;
    private LeagueDao leagueDao;

    public AppInit(TeamDao teamDao, LeagueDao leagueDao) {
        this.teamDao = teamDao;
        this.leagueDao = leagueDao;
    }

    @Override
    public void run(String... args) {
        teamDao.save(new Team("Everton"));
        leagueDao.save(new League("Premier League"));
    }
}
