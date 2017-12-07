package com.fantasy.dto;

import com.fantasy.domain.Match;
import com.fantasy.domain.Player;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
public class GoalDto {

    @NotNull
    private Player scoredBy;

    private Player assistedBy;

    @NotNull
    private Match match;

    private Integer minuteOfGame;
}
