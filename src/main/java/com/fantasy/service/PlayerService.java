package com.fantasy.service;

import com.fantasy.domain.Player;
import com.fantasy.dto.PlayerDto;
import com.fantasy.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    private final static int goalPoints = 2;
    private final static int assistPoints = 1;

    public Player getPlayer(Long id) {
        return playerRepository.findOne(id);
    }

    public Page<Player> getPlayers(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    @Transactional
    public Player addPlayer(PlayerDto playerDto) {
        Player player = new Player();

        player.setName(playerDto.getName());
        player.setSurname(playerDto.getSurname());
        player.setPhoto(playerDto.getPhoto());
        player.setPosition(playerDto.getPosition());
        player.setClub(playerDto.getClub());
        player.setShirtNumber(playerDto.getShirtNumber());
        player.setPrice(playerDto.getPrice());

        return playerRepository.save(player);
    }

    @Transactional
    public Player editPlayer(Player player, PlayerDto playerDto) {
        player.setName(playerDto.getName());
        player.setSurname(playerDto.getSurname());
        player.setPhoto(playerDto.getPhoto());
        player.setPosition(playerDto.getPosition());
        player.setClub(playerDto.getClub());
        player.setShirtNumber(playerDto.getShirtNumber());
        player.setPrice(playerDto.getPrice());

        return playerRepository.save(player);
    }

    @Transactional
    public void processGoalScored(Player player) {
        int points = player.getPoints();
        points += goalPoints;
        player.setPoints(points);
        playerRepository.save(player);
    }

    @Transactional
    public void processAssist(Player player) {
        int points = player.getPoints();
        points += assistPoints;
        player.setPoints(points);
        playerRepository.save(player);
    }
}
