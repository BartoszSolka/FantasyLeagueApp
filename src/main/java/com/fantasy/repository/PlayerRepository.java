package com.fantasy.repository;

import com.fantasy.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = "UPDATE Player p SET p.points = 0")
    public void resetPoints();
}
