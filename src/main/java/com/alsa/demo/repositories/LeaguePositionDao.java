package com.alsa.demo.repositories;

import com.alsa.demo.entities.LeaguePosition;
import com.alsa.demo.entities.LeaguePositionId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaguePositionDao extends JpaRepository<LeaguePosition, LeaguePositionId> {

    @Transactional
    @Query("SELECT l FROM LeaguePosition l WHERE l.league = (SELECT g FROM League g WHERE g.id = ?1)")
    public List<LeaguePosition> findAllByLeagueId(int leagueId);

}
