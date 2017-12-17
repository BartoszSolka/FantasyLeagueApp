package com.fantasy.controller;

import com.fantasy.domain.Player;
import com.fantasy.domain.Team;
import com.fantasy.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/{id}")
    public Team getTeam(@PathVariable("id") Long teamId) {
        return teamService.getTeam(teamId);
    }

    @GetMapping
    public Page<Team> getTeams(@PageableDefault Pageable pageable) {
        return teamService.getTeams(pageable);
    }

    @PostMapping("/{teamId}/add-player")
    public Team addPlayerToTeam(@PathVariable("teamId") Team team, @RequestBody Long playerId) {
        return teamService.addPlayerToTeam(team, playerId);
    }

    @PostMapping("/{id}/{playerId}")
    public Team setCaptain(@PathVariable("id") Team team, @PathVariable("playerId")Player player) {
        return teamService.setCaptain(team, player);
    }
}
