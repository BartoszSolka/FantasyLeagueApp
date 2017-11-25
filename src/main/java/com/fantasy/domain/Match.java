package com.fantasy.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Match {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Club home;

    @OneToMany(mappedBy = "match")
    private List<Goal> homeGoals = new ArrayList<>();

    @ManyToOne
    private Club visitor;

    @OneToMany(mappedBy = "match")
    private List<Goal> visitorGoals = new ArrayList<>();
}
