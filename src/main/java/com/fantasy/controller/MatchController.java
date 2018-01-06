package com.fantasy.controller;

import com.fantasy.domain.Match;
import com.fantasy.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/{id}")
    public Match getMatch(@PathVariable("id")Long matchId) {
        return matchService.getMatch(matchId);
    }

    @GetMapping
    public Page<Match> getMatches(@PageableDefault Pageable pageable) {
        return matchService.getMatches(pageable);
    }
}
