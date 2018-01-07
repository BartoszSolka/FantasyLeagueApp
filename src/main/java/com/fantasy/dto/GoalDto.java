package com.fantasy.dto;

import com.fantasy.enums.MatchSide;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GoalDto {

    @NotNull
    private Long scoredBy;

    private Long assistedBy;

    @NotNull
    private Long matchId;

    private MatchSide matchSide;

    private Integer minuteOfGame;
}
