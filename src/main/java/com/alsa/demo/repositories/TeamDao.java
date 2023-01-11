package com.alsa.demo.repositories;

import com.alsa.demo.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDao extends JpaRepository<Team, Integer> {

    public Team findByName(String name);

}
