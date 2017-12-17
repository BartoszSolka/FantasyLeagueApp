package com.fantasy.domain;

import com.fantasy.enums.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    private Team fantasyTeam = new Team();

    private Integer money = 1000000;

    private int points;

    @Enumerated(EnumType.STRING)
    private Role role;
}
