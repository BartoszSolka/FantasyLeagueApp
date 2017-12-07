package com.fantasy.service;

import com.fantasy.domain.Goal;
import com.fantasy.domain.Player;
import com.fantasy.dto.GoalDto;
import com.fantasy.repository.GoalRepository;
import com.fantasy.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final PlayerService playerService;

    public Goal getGoal(Long goalId) {
        return goalRepository.findOne(goalId);
    }

    public Page<Goal> getGoals(Pageable pageable) {
        return goalRepository.findAll(pageable);
    }

    @Transactional
    public Goal addGoal(GoalDto goalDto) {
        Goal goal = new Goal();
        goal.setScoredBy(goalDto.getScoredBy());
        goal.setAssistedBy(goalDto.getAssistedBy());
        goal.setMatch(goalDto.getMatch());
        goal.setMinuteOfGame(goalDto.getMinuteOfGame());

        playerService.processGoalScored(goal.getScoredBy());
        if (goal.getAssistedBy() != null) {
            playerService.processAssist(goal.getAssistedBy());
        }


        return goalRepository.save(goal);
    }

    @Transactional
    public Goal editGoal(Goal goal, GoalDto goalDto) {
        goal.setScoredBy(goalDto.getScoredBy());
        goal.setAssistedBy(goalDto.getAssistedBy());
        goal.setMatch(goalDto.getMatch());
        goal.setMinuteOfGame(goalDto.getMinuteOfGame());
        return goalRepository.save(goal);

    }
}
