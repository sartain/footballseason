package com.alsa.demo.repositories;

import com.alsa.demo.entities.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueDao extends JpaRepository<League, Integer> {

}
