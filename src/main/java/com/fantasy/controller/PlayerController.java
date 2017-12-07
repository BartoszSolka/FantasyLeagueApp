package com.fantasy.controller;

import com.fantasy.domain.Player;
import com.fantasy.dto.PlayerDto;
import com.fantasy.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable("id") Long id) {
        return playerService.getPlayer(id);
    }

    @GetMapping
    public Page<Player> getPlayers(@PageableDefault Pageable pageable) {
        return playerService.getPlayers(pageable);
    }

    @PostMapping
    public Player addPlayer(@RequestBody PlayerDto playerDto) {
        return playerService.addPlayer(playerDto);
    }

    @PostMapping("/{id}")
    public Player editPlayer(@PathVariable("id") Player player, @RequestBody PlayerDto playerDto) {
        return playerService.editPlayer(player, playerDto);
    }
}
