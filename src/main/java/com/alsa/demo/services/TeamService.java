package com.alsa.demo.services;

import com.alsa.demo.entities.Team;
import com.alsa.demo.repositories.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    TeamDao dao;

    public TeamService(TeamDao dao) {
        this.dao = dao;
    }

    public Team getTeamFromId(int id) {
        return dao.getReferenceById(id);
    }

    public void saveTeam(String name) {
        dao.save(new Team(name));
    }


}
