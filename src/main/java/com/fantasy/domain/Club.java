package com.fantasy.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Club {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @OneToOne
    private Photo logo;

    @OneToMany(mappedBy = "club")
    private List<Player> players = new ArrayList<>();

    @ManyToMany
    private List<Match> matches = new ArrayList<>();
}
