package com.fantasy.controller;

import com.fantasy.domain.Gameweek;
import com.fantasy.domain.Match;
import com.fantasy.dto.CreateMatchDto;
import com.fantasy.service.GameweekService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gameweek")
@RequiredArgsConstructor
public class GameweekController {

    private final GameweekService gameweekService;


    @GetMapping("/{id}")
    public Gameweek getGameweek(@PathVariable("id") Long gameweekId) {
        return gameweekService.getGameweek(gameweekId);
    }

    @GetMapping
    public Page<Gameweek> getGameweeks(@PageableDefault Pageable pageable) {
        return gameweekService.getGameweeks(pageable);
    }

    @PostMapping
    public Gameweek addGameweek(@RequestBody List<CreateMatchDto> matches) {
        return gameweekService.addGameweek(matches);
    }

    @PostMapping("/{id}")
    public Gameweek editGameweek(@PathVariable Gameweek gameweek, @RequestBody List<CreateMatchDto> matches) {
        return gameweekService.editGameweek(gameweek, matches);
    }
}
