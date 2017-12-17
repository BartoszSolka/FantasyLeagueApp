package com.fantasy.service;

import com.fantasy.domain.*;
import com.fantasy.dto.CreateMatchDto;
import com.fantasy.repository.ClubRepository;
import com.fantasy.repository.GameweekRepository;
import com.fantasy.repository.PlayerRepository;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameweekService {

    private final GameweekRepository gameweekRepository;
    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;

    public Gameweek getGameweek(Long gameweekId) {
        return gameweekRepository.findOne(gameweekId);
    }

    public Page<Gameweek> getGameweeks(Pageable pageable) {
        return gameweekRepository.findAll(pageable);
    }

    @Transactional
    public Gameweek addGameweek(List<CreateMatchDto> matches) {
        Gameweek gameweek = new Gameweek();
        List<Match> matchList = new ArrayList<>();
        processMatchDto(matches, matchList);

        gameweek.getMatches().addAll(matchList);
        return gameweekRepository.save(gameweek);
    }

    @Transactional
    public Gameweek editGameweek(Gameweek gameweek, List<CreateMatchDto> matches) {
        gameweek.getMatches().clear();
        List<Match> matchList = new ArrayList<>();
        processMatchDto(matches, matchList);

        gameweek.getMatches().addAll(matchList);
        return gameweekRepository.save(gameweek);
    }

    private void processMatchDto(List<CreateMatchDto> matches, List<Match> matchList) {
        matches.forEach(createMatchDto -> {
            Match match = new Match();
            Club home = clubRepository.findOne(createMatchDto.getHomeClubId());
            Club visitor = clubRepository.findOne(createMatchDto.getVisitorClubId());
            match.setHome(home);
            match.setVisitor(visitor);
            matchList.add(match);
        });
    }

    public Gameweek setGameweekAsCurrent(Gameweek gameweek) {
        List<Gameweek> gameweeks = Lists.newArrayList(gameweekRepository.findAll(QGameweek.gameweek.current.isTrue()));
        
        if (gameweeks.contains(gameweek)) {
            return gameweek;
        }
        gameweeks.forEach(gameweek1 -> gameweek1.setCurrent(false));
        gameweek.setCurrent(true);
        gameweekRepository.save(gameweeks);

        playerRepository.resetPoints();
        return gameweekRepository.save(gameweek);
    }
}
