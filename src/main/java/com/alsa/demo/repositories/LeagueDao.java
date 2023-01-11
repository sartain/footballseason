package com.alsa.demo.repositories;

import com.alsa.demo.entities.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeagueDao extends JpaRepository<League, Integer> {

    public Optional<League> findByName(String name);

}
