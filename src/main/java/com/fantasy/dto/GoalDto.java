package com.fantasy.dto;

import com.fantasy.domain.Player;
import com.fantasy.enums.MatchSide;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GoalDto {

    @NotNull
    private Player scoredBy;

    private Player assistedBy;

    @NotNull
    private Long matchId;

    private MatchSide matchSide;

    private Integer minuteOfGame;
}
