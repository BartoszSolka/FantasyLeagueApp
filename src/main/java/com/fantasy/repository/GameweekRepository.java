package com.fantasy.repository;

import com.fantasy.domain.Gameweek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface GameweekRepository extends JpaRepository<Gameweek, Long>, QueryDslPredicateExecutor<Gameweek> {
}
