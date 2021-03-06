package com.fantasy.service;

import com.fantasy.domain.Club;
import com.fantasy.domain.Gameweek;
import com.fantasy.domain.Match;
import com.fantasy.domain.QGameweek;
import com.fantasy.dto.CreateGameweekDto;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameweekService {

    private final GameweekRepository gameweekRepository;
    private final ClubRepository clubRepository;
    private final PlayerRepository playerRepository;
    private final MatchService matchService;

    public Gameweek getGameweek(Long gameweekId) {
        return gameweekRepository.findOne(gameweekId);
    }

    public Page<Gameweek> getGameweeks(Pageable pageable) {
        return gameweekRepository.findAll(pageable);
    }

    @Transactional
    public Gameweek addGameweek(CreateGameweekDto createGameweekDto) {
        Gameweek gameweek = new Gameweek();
        gameweek.setName(createGameweekDto.getName());

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

    public Gameweek addMatch(Long gameweekId, CreateMatchDto createMatchDto) {
        Gameweek gameweek = gameweekRepository.findOne(gameweekId);
        Match match = new Match();
        Club home = clubRepository.findOne(createMatchDto.getHomeClubId());
        Club visitor = clubRepository.findOne(createMatchDto.getVisitorClubId());
        match.setHome(home);
        match.setVisitor(visitor);

        gameweek.getMatches().add(match);
        return gameweekRepository.save(gameweek);
    }
}
