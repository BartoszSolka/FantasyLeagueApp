package com.fantasy.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Gameweek {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Match> matches = new ArrayList<>();
}
