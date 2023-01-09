package com.alsa.demo.services;

import com.alsa.demo.entities.League;
import com.alsa.demo.repositories.LeagueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueService {

    @Autowired
    LeagueDao dao;

    public LeagueService(LeagueDao dao) {
        this.dao = dao;
    }

    public League getLeagueFromId(int id) {
        return dao.getReferenceById(id);
    }


}
