package com.fantasy.service;

import com.fantasy.domain.Club;
import com.fantasy.domain.Gameweek;
import com.fantasy.domain.Goal;
import com.fantasy.domain.Match;
import com.fantasy.dto.CreateMatchDto;
import com.fantasy.repository.ClubRepository;
import com.fantasy.repository.GameweekRepository;
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
}
