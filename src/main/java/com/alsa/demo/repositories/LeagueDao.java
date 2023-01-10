package com.alsa.demo.repositories;

import com.alsa.demo.entities.League;
import com.alsa.demo.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueDao extends JpaRepository<League, Integer> {

    public League getReferenceByName(String name);

}
