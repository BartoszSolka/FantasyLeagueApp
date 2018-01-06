package com.fantasy.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Gameweek {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Match> matches = new ArrayList<>();

    private boolean current;
}
