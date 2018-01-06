package com.fantasy.service;

import com.fantasy.domain.Goal;
import com.fantasy.domain.Match;
import com.fantasy.dto.GoalDto;
import com.fantasy.enums.MatchSide;
import com.fantasy.repository.GoalRepository;
import com.fantasy.repository.MatchRepository;
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
    private final MatchRepository matchRepository;

    public Goal getGoal(Long goalId) {
        return goalRepository.findOne(goalId);
    }

    public Page<Goal> getGoals(Pageable pageable) {
        return goalRepository.findAll(pageable);
    }

    @Transactional
    public Goal addGoal(GoalDto goalDto) {
        Goal goal = new Goal();
        Match match = matchRepository.findOne(goalDto.getMatchId());
        goal.setScoredBy(goalDto.getScoredBy());
        goal.setAssistedBy(goalDto.getAssistedBy());
        goal.setMatch(match);
        goal.setMinuteOfGame(goalDto.getMinuteOfGame());

        playerService.processGoalScored(goal.getScoredBy());
        if (goal.getAssistedBy() != null) {
            playerService.processAssist(goal.getAssistedBy());
        }

        goal = goalRepository.save(goal);

        if (MatchSide.HOME.equals(goalDto.getMatchSide())) {
            match.getHomeGoals().add(goal);
        } else if (MatchSide.VISITOR.equals(goalDto.getMatchSide())) {
            match.getVisitorGoals().add(goal);
        }

        matchRepository.save(match);
        return goal;
    }

    @Transactional
    public Goal editGoal(Goal goal, GoalDto goalDto) {
        Match match = matchRepository.findOne(goalDto.getMatchId());
        goal.setScoredBy(goalDto.getScoredBy());
        goal.setAssistedBy(goalDto.getAssistedBy());
        goal.setMatch(match);
        goal.setMinuteOfGame(goalDto.getMinuteOfGame());
        return goalRepository.save(goal);

    }
}
