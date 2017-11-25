package com.fantasy.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Club {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne
    private Photo logo;

    @OneToMany
    private List<Player> players = new ArrayList<>();

    @ManyToMany
    private List<Match> matches = new ArrayList<>();
}
