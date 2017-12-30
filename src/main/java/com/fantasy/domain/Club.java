package com.fantasy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Player> players = new ArrayList<>();

    @ManyToMany
    private List<Match> matches = new ArrayList<>();

    public Club() {
    }

    public Club(String name) {
        this.name = name;
    }
}
