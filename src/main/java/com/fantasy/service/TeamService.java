package com.fantasy.service;

import com.fantasy.domain.Club;
import com.fantasy.domain.Player;
import com.fantasy.domain.Team;
import com.fantasy.domain.User;
import com.fantasy.repository.PlayerRepository;
import com.fantasy.repository.TeamRepository;
import com.fantasy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final UserService userService;

    public Team getTeam(Long teamId) {
        return teamRepository.findOne(teamId);
    }

    public Page<Team> getTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    @Transactional
    public Team addPlayerToTeam(Team team, Long playerId) {
        Player player = playerRepository.findOne(playerId);
        if (player == null) {
            return null;
        }
        User user = team.getOwner();
        if (!userService.readCurrent().equals(user)) {
            return null;
        }
        if (user.getMoney() >= player.getPrice()) {
            team.getPlayers().add(player);
            user.setMoney(user.getMoney() - player.getPrice());
            team = teamRepository.save(team);
            user.setFantasyTeam(team);
            userRepository.save(user);
        }
        return team;
    }

    @Transactional
    public Team setCaptain(Team team, Player player) {
        team.setCaptain(player);
        return teamRepository.save(team);
    }
}
