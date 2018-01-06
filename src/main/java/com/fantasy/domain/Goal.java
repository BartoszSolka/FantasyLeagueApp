package com.fantasy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Goal {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Player scoredBy;

    @ManyToOne
    private Player assistedBy;

    @ManyToOne
    @JsonIgnore
    private Match match;

    private Integer minuteOfGame;
}
