package com.fantasy.service;

import com.fantasy.domain.Goal;
import com.fantasy.domain.Match;
import com.fantasy.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public Match getMatch(Long matchId) {
        Match match = matchRepository.findOne(matchId);
        match.getHomeGoals().sort(Comparator.comparing(Goal::getMinuteOfGame));
        match.getVisitorGoals().sort(Comparator.comparing(Goal::getMinuteOfGame));
        return match;
    }

    public Page<Match> getMatches(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }
}
