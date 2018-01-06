package com.fantasy.controller;

import com.fantasy.domain.Gameweek;
import com.fantasy.dto.CreateGameweekDto;
import com.fantasy.dto.CreateMatchDto;
import com.fantasy.service.GameweekService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Gameweek addGameweek(@Valid @RequestBody CreateGameweekDto createGameweekDto) {
        return gameweekService.addGameweek(createGameweekDto);
    }

    @PostMapping("/{id}")
    public Gameweek editGameweek(@PathVariable Gameweek gameweek, @RequestBody List<CreateMatchDto> matches) {
        return gameweekService.editGameweek(gameweek, matches);
    }

    @PutMapping("/{id}/current")
    public Gameweek setGameweekAsCurrent(@PathVariable Gameweek gameweek) {
        return gameweekService.setGameweekAsCurrent(gameweek);
    }

    @PostMapping("{id}/match")
    public Gameweek addMatch(@PathVariable("id") Long gameweekId, @RequestBody CreateMatchDto matchDto) {
        return gameweekService.addMatch(gameweekId, matchDto);
    }
}
