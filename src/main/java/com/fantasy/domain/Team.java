package com.fantasy.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User owner;

    @ManyToMany
    private List<Player> players = new ArrayList<>();

    private Integer points;
}
