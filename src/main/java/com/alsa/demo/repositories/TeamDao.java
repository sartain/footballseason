package com.alsa.demo.repositories;

import com.alsa.demo.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamDao extends JpaRepository<Team, Integer> {

    public Optional<Team> findByName(String name);

}
