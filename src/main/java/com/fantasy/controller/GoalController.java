package com.fantasy.controller;

import com.fantasy.domain.Goal;
import com.fantasy.dto.GoalDto;
import com.fantasy.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goal")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    @GetMapping("/{id}")
    public Goal getGoal(@PathVariable("id") Long goalId) {
        return goalService.getGoal(goalId);
    }

    @GetMapping
    public Page<Goal> getGoals(@PageableDefault Pageable pageable) {
        return goalService.getGoals(pageable);
    }

    @PostMapping
    public Goal addGoal(@RequestBody GoalDto goalDto) {
        return goalService.addGoal(goalDto);
    }

    @PostMapping("/{id}")
    public Goal editGoal(@PathVariable("id") Goal goal, @RequestBody GoalDto goalDto) {
        return goalService.editGoal(goal, goalDto);
    }
}
