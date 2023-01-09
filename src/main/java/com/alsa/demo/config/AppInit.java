package com.alsa.demo.config;

import com.alsa.demo.entities.Team;
import com.alsa.demo.repositories.TeamDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private TeamDao teamDao;

    public AppInit(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Override
    public void run(String... args) {
        teamDao.save(new Team("Everton"));
    }
}
