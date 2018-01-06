package com.fantasy.service;

import com.fantasy.domain.Match;
import com.fantasy.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public Match getMatch(Long matchId) {
        return matchRepository.findOne(matchId);
    }

    public Page<Match> getMatches(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }
}
