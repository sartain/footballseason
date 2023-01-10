package com.alsa.demo.repositories;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.LeaguePositionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaguePositionDao extends JpaRepository<LeaguePosition, LeaguePositionId> {
}
