package com.fantasy.repository;

import com.fantasy.domain.Gameweek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameweekRepository extends JpaRepository<Gameweek, Long> {
}
